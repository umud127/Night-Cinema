package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.jwt.*;
import az.duo.Night.Cinema.repository.RestUserRepo;
import az.duo.Night.Cinema.service.IRestAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestAuthServiceIMPL implements IRestAuthService {

    private final JWTService jwtService;
    private final RestUserRepo restUserRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public BaseEntity<AuthResponse> register(RegisterRequest request) {
        if(request.getEmail() == null || request.getPassword() == null || request.getUsername() == null || request.getPhoneNumber() == null) {
            return BaseEntity.notOk(StatusCode.BAD_REQUEST, "email, password, username and phoneNumber are required", "/register");
        }

        if(restUserRepo.existsByEmail(request.getEmail())) {
            return BaseEntity.notOk(StatusCode.BAD_REQUEST, "email is already taken", "/register");
        }

        if(restUserRepo.existsByUsername(request.getUsername())) {
            return BaseEntity.notOk(StatusCode.BAD_REQUEST, "username is already taken", "/register");
        }

        User newUser = new User();

        newUser.setEmail(request.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setPhoneE164(request.getPhoneNumber());

        newUser.setUsername(request.getUsername());

        restUserRepo.save(newUser);

        AuthResponse response = new AuthResponse();

        response.setAccessToken(jwtService.generateToken(newUser));
        response.setRefreshToken(jwtService.generateRefreshToken(newUser));

        return BaseEntity.ok(response);
    }

    @Override
    public BaseEntity<AuthResponse> authenticate(AuthRequest request) {
        if(request.getEmail() == null || request.getPassword() == null) {
            return BaseEntity.notOk(StatusCode.BAD_REQUEST, "email and password are required", "/login");
        }

        User user = restUserRepo.findByEmail(request.getEmail()).orElse(null);

        if(user != null && bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            AuthResponse response = new AuthResponse();

            response.setAccessToken(jwtService.generateToken(user));
            response.setRefreshToken(jwtService.generateRefreshToken(user));

            return BaseEntity.ok(response);
        }

        if(user == null) {
            return BaseEntity.notOk(StatusCode.NOT_FOUND, "email not found", "/login");
        } else {
            return BaseEntity.notOk(StatusCode.UNAUTHORIZED, "password is incorrect", "/login");
        }
    }

    @Override
    public BaseEntity<AuthResponse> refreshToken(RefreshTokenRequest request) {

        String refreshToken = request.getRefreshToken();
        if(refreshToken == null || refreshToken.isBlank()) {
            return BaseEntity.notOk(StatusCode.BAD_REQUEST, "refreshToken is required", "/refresh");
        }

        boolean tokenIsExpired = jwtService.isTokenExpired(refreshToken);

        String username;
        if (!tokenIsExpired) {
            username = jwtService.extractUsername(refreshToken);
        } else {
            return BaseEntity.notOk(StatusCode.UNAUTHORIZED, "refreshToken is expired", "/refresh");
        }

        User user = restUserRepo.findByEmail(username).orElse(null);

        if(user != null) {
            AuthResponse response = new AuthResponse();

            response.setAccessToken(jwtService.generateToken(user));
            response.setRefreshToken(jwtService.generateRefreshToken(user));

            return BaseEntity.ok(response);
        }

        return BaseEntity.notOk(StatusCode.NOT_FOUND, "User Not Found", "/refresh");
    }
}
