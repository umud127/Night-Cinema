package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.exception.BadRequestException;
import az.duo.Night.Cinema.exception.DataInsertException;
import az.duo.Night.Cinema.exception.NotFoundException;
import az.duo.Night.Cinema.exception.UnauthorizedUserException;
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

        if(request.getPassword().length() < 8) {
            throw new BadRequestException("password must be at least 8 characters long", "/register");
        }


        if(restUserRepo.existsByEmail(request.getEmail())) {
            throw new DataInsertException("email is already taken", "/register");
        }

        if(restUserRepo.existsByUsername(request.getUsername())) {
            throw new DataInsertException("username is already taken", "/register");
        }

        User newUser = new User();

        newUser.setEmail(request.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        newUser.setPhoneE164(request.getPhoneNumber());
        newUser.setPhoneE164(request.getPhoneNumber());

        newUser.setUsername(request.getUsername());

        newUser.setProfilePhotoUrl("https://res.cloudinary.com/dvusim2rf/image/upload/v1760720515/hu8eqetp1qmn4krc2mdk.webp");

        restUserRepo.save(newUser);

        AuthResponse response = new AuthResponse();

        response.setAccessToken(jwtService.generateToken(newUser));
        response.setRefreshToken(jwtService.generateRefreshToken(newUser));

        return BaseEntity.ok(response);
    }

    @Override
    public BaseEntity<AuthResponse> authenticate(AuthRequest request) {
        if(request.getEmailOrUsername() == null || request.getPassword() == null) {
            throw new BadRequestException("email and password are required", "/login");
        }

        User user;

        if (request.getEmailOrUsername().contains("@")) {
            user = restUserRepo.findByEmail(request.getEmailOrUsername()).orElse(null);
        } else {
            user = restUserRepo.findUserByUsername(request.getEmailOrUsername()).orElse(null);
        }

        if (user == null) {
            user = restUserRepo.findAdminByUsername(request.getEmailOrUsername()).orElse(null);
        }

        if (user != null) {
            if (bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
                AuthResponse response = new AuthResponse();

                response.setAccessToken(jwtService.generateToken(user));
                response.setRefreshToken(jwtService.generateRefreshToken(user));

                return BaseEntity.ok(response);
            } else {
                throw new UnauthorizedUserException("email and password do not match", "/login");
            }
        }

        throw new NotFoundException("email or username not found", "/login");
    }

    @Override
    public BaseEntity<AuthResponse> refreshToken(RefreshTokenRequest request) {

        String refreshToken = request.getRefreshToken();

        if(refreshToken == null || refreshToken.isBlank()) {
            throw new BadRequestException("refreshToken is required", "/refresh");
        }

        boolean tokenIsExpired = jwtService.isTokenExpired(refreshToken);

        Long id;

        if (!tokenIsExpired) {
            id = jwtService.extractIdFromToken(refreshToken);
        } else {
            throw new UnauthorizedUserException("refreshToken is expired", "/refresh");
        }

        User user = restUserRepo.findById(id).orElse(null);

        if(user != null) {
            AuthResponse response = new AuthResponse();

            response.setAccessToken(jwtService.generateToken(user));
            response.setRefreshToken(jwtService.generateRefreshToken(user));

            return BaseEntity.ok(response);
        }

        throw new NotFoundException("User Not Found", "/refresh");
    }
}
