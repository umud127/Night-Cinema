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

        User newUser = new User();

        newUser.setEmail(request.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setUsername(request.getUsername());

        restUserRepo.save(newUser);

        AuthResponse response = new AuthResponse();

        response.setAccessToken(jwtService.generateToken(newUser));
        response.setRefreshToken(jwtService.generateRefreshToken(newUser));

        return BaseEntity.ok(response);
    }

    @Override
    public BaseEntity<AuthResponse> authentication(AuthRequest request) {
        return null;
    }

    @Override
    public BaseEntity<AuthResponse> refreshToken(RefreshTokenRequest request) {
        return null;
    }
}
