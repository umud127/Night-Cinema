package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.jwt.AuthRequest;
import az.duo.Night.Cinema.jwt.AuthResponse;
import az.duo.Night.Cinema.jwt.RefreshTokenRequest;
import az.duo.Night.Cinema.jwt.RegisterRequest;

public interface IRestAuthController {

    BaseEntity<AuthResponse> register(RegisterRequest request);

    BaseEntity<AuthResponse> authentication(AuthRequest request);

    BaseEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
