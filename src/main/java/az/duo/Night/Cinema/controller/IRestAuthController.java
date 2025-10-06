package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.jwt.AuthRequest;
import az.duo.Night.Cinema.jwt.AuthResponse;
import az.duo.Night.Cinema.jwt.RefreshTokenRequest;
import az.duo.Night.Cinema.jwt.RegisterRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface IRestAuthController {

    BaseEntity<AuthResponse> register(RegisterRequest request);

    BaseEntity<AuthResponse> authenticate(AuthRequest request);

    BaseEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
