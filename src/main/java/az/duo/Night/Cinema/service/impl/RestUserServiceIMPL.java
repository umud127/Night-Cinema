package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.movie.DTOMovie;
import az.duo.Night.Cinema.dto.user.DTOUserIU;
import az.duo.Night.Cinema.dto.user.DTOUserInfo;
import az.duo.Night.Cinema.dto.user.DTOUserMovie;
import az.duo.Night.Cinema.dto.user.DTOUserSecurity;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.MovieSession;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.jwt.JWTService;
import az.duo.Night.Cinema.repository.RestUserRepo;
import az.duo.Night.Cinema.service.IRestUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            return BaseEntity.notOk(StatusCode.UNAUTHORIZED, "token is invalid", "/me");
        }

        if(!restUserRepo.existsById(id)) {
            return BaseEntity.notOk(StatusCode.NOT_FOUND, "User Not Found", "/me");
        }

        Optional<User> dbUser = restUserRepo.findById(id);

        if(dbUser.isPresent()) {
            DTOUserInfo user = new DTOUserInfo();
            BeanUtils.copyProperties(dbUser.get(), user);
            //eger menimsedilmeyen varsa manual menimsedecem
            return BaseEntity.ok(user);
        }

        return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, "Server Error: Unexpected Error", "/me");
    }

    public BaseEntity<DTOUserSecurity> getUserSecurity(String token) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            return BaseEntity.notOk(StatusCode.UNAUTHORIZED, "token is invalid", "/me");
        }

        if(!restUserRepo.existsById(id)) {
            return BaseEntity.notOk(StatusCode.NOT_FOUND, "User Not Found", "/me");
        }

        Optional<User> dbUser = restUserRepo.findById(id);

        if(dbUser.isPresent()) {
            DTOUserSecurity user = new DTOUserSecurity();
            BeanUtils.copyProperties(dbUser.get(), user);
            //eger menimsedilmeyen varsa manual menimsedecem
            return BaseEntity.ok(user);
        }

        return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, "Server Error: Unexpected Error", "/me");
    }

    public BaseEntity<DTOUserMovie> getUserMovie(String token) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            return BaseEntity.notOk(StatusCode.UNAUTHORIZED, "token is invalid", "/me");
        }

        if(!restUserRepo.existsById(id)) {
            return BaseEntity.notOk(StatusCode.NOT_FOUND, "User Not Found", "/me");
        }

        Optional<List<MovieSession>> dbUser = restUserRepo.findMovieSessionsByUserId(id);

        if(dbUser.isPresent()) {
            DTOUserMovie user = new DTOUserMovie();

            List<MovieSession> movies = dbUser.get();
            List<DTOMovie> dtoMovies = new java.util.ArrayList<>();

            for(MovieSession movieSession : movies) {
                DTOMovie dtoMovie = new DTOMovie();

                dtoMovie.setName(movieSession.getMovie().getName());
                dtoMovie.setDescription(movieSession.getMovie().getDescription());
                dtoMovie.setDate(movieSession.getStartTime());

                dtoMovie.setCoverPhotoUrl(movieSession.getMovie().getCoverPhotoUrl());

                dtoMovies.add(dtoMovie);
            }

            user.setGotMovies(dtoMovies.size());
            user.setMovies(dtoMovies);

            return BaseEntity.ok(user);
        }

        return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, "Server Error: Unexpected Error", "/me");
    }

    @Override
    public BaseEntity<DTOUserSecurity> updateUser(String token, DTOUserIU user) {
        return null;
    }
}
