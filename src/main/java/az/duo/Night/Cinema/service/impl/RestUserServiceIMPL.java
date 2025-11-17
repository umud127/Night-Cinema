package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.movie.DTOMovie2;
import az.duo.Night.Cinema.dto.user.DTOUserIU;
import az.duo.Night.Cinema.dto.user.DTOUserInfo;
import az.duo.Night.Cinema.dto.user.DTOUserMovie;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.MovieSession;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.RoleName;
import az.duo.Night.Cinema.exception.DataInsertException;
import az.duo.Night.Cinema.exception.NotFoundException;
import az.duo.Night.Cinema.exception.UnauthorizedUserException;
import az.duo.Night.Cinema.jwt.JWTService;
import az.duo.Night.Cinema.repository.RestUserRepo;
import az.duo.Night.Cinema.service.IRestUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestUserServiceIMPL implements IRestUserService {

    private final RestUserRepo restUserRepo;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public BaseEntity<RoleName> checkUserRole(String token) {
        if (token == null) {
            throw new UnauthorizedUserException("token is invalid", "/user/check");
        }

        String email = jwtService.extractUsername(token);

        if (email == null) {
            throw new UnauthorizedUserException("token is invalid", "/user/check");
        }

        if(!restUserRepo.existsByEmail(email)) {
            throw new NotFoundException("User Not Found with username: " + email, "/user/check");
        }

        RoleName role = restUserRepo.getRoleByEmail(email);

        return BaseEntity.ok(role);
    }

    public BaseEntity<DTOUserInfo> getUserInfo(String token) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            throw new UnauthorizedUserException("token is invalid", "/user/info");
        }

        if(!restUserRepo.existsById(id)) {
            throw new NotFoundException("User Not Found", "/user/info");
        }

        DTOUserInfo dbUser = restUserRepo.getUserInfoById(id)
                .orElseThrow(() -> new NotFoundException("User Info Not Found", "/user/info"));

        return BaseEntity.ok(dbUser);
    }

    public BaseEntity<DTOUserMovie> getUserMovie(String token) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            throw new UnauthorizedUserException("token is invalid", "/user/movie");
        }

        List<MovieSession> sessions = restUserRepo.findMovieSessionsByUserId(id)
                .orElse(new ArrayList<>());

        DTOUserMovie userMovie = new DTOUserMovie();

        List<DTOMovie2> dtoMovies = new ArrayList<>();

        for(MovieSession movieSession : sessions) {
            DTOMovie2 dtoMovie2 = new DTOMovie2();

            dtoMovie2.setName(movieSession.getMovie().getName());
            dtoMovie2.setDescription(movieSession.getMovie().getDescription());
            dtoMovie2.setStartTime(movieSession.getStartTime());
            dtoMovie2.setMovieDuration(movieSession.getMovie().getMovieDuration());
            dtoMovie2.setCoverPhotoUrl(movieSession.getMovie().getCoverPhotoUrl());

            dtoMovies.add(dtoMovie2);
        }

        userMovie.setGotMovies(dtoMovies.size());
        userMovie.setMovies(dtoMovies);

        return BaseEntity.ok(userMovie);
    }

    @Override
    public BaseEntity<String> updateUser(String token, DTOUserIU user) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            throw new UnauthorizedUserException("token is invalid", "/user/update");
        }

        User dbUser =  restUserRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User Not Found", "/user/update"));

        if (user.getPhoneNumber() != null) {
            dbUser.setPhoneE164(user.getPhoneNumber());
        }
        if (user.getEmail() != null && !restUserRepo.existsByEmail(user.getEmail())) {
            dbUser.setEmail(user.getEmail());
        } else {
            throw new DataInsertException("Email already taken", "/user/update");
        }
        if (user.getUsername() != null && !restUserRepo.existsByUsername(user.getUsername())) {
            dbUser.setUsername(user.getUsername());
        } else {
            throw new DataInsertException("Username already taken", "/user/update");
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().isBlank()) {
            dbUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        restUserRepo.save(dbUser);

        return BaseEntity.ok("User Updated Successfully");
    }
}