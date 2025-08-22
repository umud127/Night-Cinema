package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.jwt.JWTService;
import az.duo.Night.Cinema.repository.RestUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestUserServiceIMPL {

    private final RestUserRepo restUserRepo;
    private final JWTService jwtService;

    public User getUser(String token) {
        Long id = jwtService.extractIdFromToken(token);
        return restUserRepo.findById(id).orElse(null);
    }
}
