package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.exception.BadRequestException;
import az.duo.Night.Cinema.jwt.*;
import az.duo.Night.Cinema.repository.RestUserRepo;
import az.duo.Night.Cinema.service.IRestAuthService;
import jakarta.transaction.Transactional;
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
    @Transactional(rollbackOn = Exception.class)
    public BaseEntity<AuthResponse> register(RegisterRequest request) {
        if(request.getEmail() == null || request.getPassword() == null || request.getUsername() == null || request.getPhoneNumber() == null) {
            throw new BadRequestException("email, password, username and phoneNumber are required", "/register");
        }

        if(restUserRepo.existsByEmail(request.getEmail())) {
            throw new BadRequestException("email is already taken", "/register");
        }

        if(restUserRepo.existsByUsername(request.getUsername())) {
            throw new BadRequestException("username is already taken", "/register");
        }

        if(request.getPassword().length() < 8) {
            throw new BadRequestException("password must be at least 8 characters long", "/register");
        }

        User newUser = new User();

        newUser.setEmail(request.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setPhoneE164(request.getPhoneNumber());

        newUser.setUsername(request.getUsername());

        newUser.setProfilePhotoUrl("http://res.cloudinary.com/dvusim2rf/image/upload/v1760720515/hu8eqetp1qmn4krc2mdk.webp");

        restUserRepo.save(newUser);

        AuthResponse response = new AuthResponse();

        response.setAccessToken(jwtService.generateToken(newUser));
        response.setRefreshToken(jwtService.generateRefreshToken(newUser));

        return BaseEntity.ok(response);
    }

    @Override
    public BaseEntity<AuthResponse> authenticate(AuthRequest request) {
        if(request.getEmail() == null || request.getPassword() == null) {
            throw new BadRequestException("email and password are required", "/login");
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

        throw new BadRequestException("User Not Found", "/refresh");
    }
}
