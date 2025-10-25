package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.movie.DTOMovie2;
import az.duo.Night.Cinema.dto.user.DTOUserIU;
import az.duo.Night.Cinema.dto.user.DTOUserInfo;
import az.duo.Night.Cinema.dto.user.DTOUserMovie;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.MovieSession;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.exception.NotFoundException;
import az.duo.Night.Cinema.exception.UnauthorizedUserException;
import az.duo.Night.Cinema.jwt.JWTService;
import az.duo.Night.Cinema.repository.RestUserRepo;
import az.duo.Night.Cinema.service.IRestUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestUserServiceIMPL implements IRestUserService {

    private final RestUserRepo restUserRepo;
    private final JWTService jwtService;

    public BaseEntity<DTOUserInfo> getUserInfo(String token) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            throw new UnauthorizedUserException("token is invalid", "/user/me");
        }

        if(!restUserRepo.existsById(id)) {
            throw new NotFoundException("User Not Found", "/user/me");
        }

        Optional<User> dbUser = restUserRepo.findById(id);

        DTOUserInfo user = new DTOUserInfo();

        user.setUsername(dbUser.get().getRealUsername());
        user.setPhoneNumber(dbUser.get().getPhoneE164());
        user.setCreatedAt(dbUser.get().getCreatedAt());
        user.setProfilePhotoUrl(dbUser.get().getProfilePhotoUrl());

        return BaseEntity.ok(user);
    }

    public BaseEntity<DTOUserMovie> getUserMovie(String token) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            throw new UnauthorizedUserException("token is invalid", "/user/movie");
        } else if(!restUserRepo.existsById(id)) {
            throw new NotFoundException("User Not Found", "/user/movie");
        }

        Optional<List<MovieSession>> dbUser = restUserRepo.findMovieSessionsByUserId(id);

        DTOUserMovie userMovie = new DTOUserMovie();

        List<MovieSession> movies = dbUser.get();
        List<DTOMovie2> dtoMovies = new ArrayList<>();

        for(MovieSession movieSession : movies) {
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
        } else if(!restUserRepo.existsById(id)) {
            throw  new NotFoundException("User Not Found", "/user/update");
        } else {
            restUserRepo.findById(id).ifPresent(user1 -> {
                if (user.getPhoneNumber() != null) {
                    user1.setPhoneE164(user.getPhoneNumber());
                }
                if (user.getEmail() != null) {
                    user1.setEmail(user.getEmail());
                }
                if (user.getUsername() != null) {
                    user1.setUsername(user.getUsername());
                }
                if (user.getPassword() != null) {
                    user1.setPassword(user.getPassword());
                }

                restUserRepo.save(user1);
            });

            return BaseEntity.ok("User Updated Successfully");
        }
    }

}
