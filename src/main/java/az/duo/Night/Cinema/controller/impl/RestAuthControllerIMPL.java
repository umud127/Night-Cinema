package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestAuthController;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.jwt.AuthRequest;
import az.duo.Night.Cinema.jwt.AuthResponse;
import az.duo.Night.Cinema.jwt.RefreshTokenRequest;
import az.duo.Night.Cinema.jwt.RegisterRequest;
import az.duo.Night.Cinema.service.IRestAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RestAuthControllerIMPL implements IRestAuthController {

    private final IRestAuthService restAuthService;

    @PostMapping("/register")
    @Override
    public BaseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return restAuthService.register(request);
    }

    @PostMapping("/login")
    @Override
    public BaseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return restAuthService.authenticate(request);
    }

    @PostMapping("/refresh_token")
    @Override
    public BaseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return restAuthService.refreshToken(request);
    }
}
