package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.AdminUserDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.Movie;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.exception.DataInsertException;
import az.duo.Night.Cinema.service.IRestAdminService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RestAdminServiceIMPL implements IRestAdminService {

    @Override
    @Transactional
    public BaseEntity<String> addMovie(AdminMovieDTO movie) {
        if(
                movie.getName() == null ||
                movie.getDescription() == null ||
                movie.getCoverPhotoUrl() == null ||
                movie.getBackgroundImgUrl() == null ||

                movie.getMovieDuration() == null ||
                movie.getGenre() == null ||

                movie.getDirector() == null ||
                movie.getActors() == null ||

                movie.getReleaseDate() == null ||
                movie.getTrailerUrl() == null ||

                movie.isStarMovie()
        ) {
            return BaseEntity.notOk(StatusCode.BAD_REQUEST, "Some of the fields are empty", null);
        }

        Movie newMovie = new Movie();

        newMovie.setName(movie.getName());
        newMovie.setDescription(movie.getDescription());
        newMovie.setCoverPhotoUrl(movie.getCoverPhotoUrl());

        newMovie.setMovieDuration(movie.getMovieDuration());
        newMovie.setGenres(movie.getGenre());

        newMovie.setDirector(movie.getDirector());
        newMovie.setActors(movie.getActors());

        newMovie.setReleaseDate(movie.getReleaseDate());
        newMovie.setTrailerUrl(movie.getTrailerUrl());
        newMovie.setStarMovie(movie.isStarMovie());

        return BaseEntity.ok("Movie was added successfully");
    }

    @Override
    public BaseEntity<String> deleteMovie(Long id) {
        return null;
    }

    @Override
    public BaseEntity<String> updateMovie(AdminMovieDTO movie) {
        return null;
    }

    @Override
    public BaseEntity<String> deleteUser(Long id) {
        return null;
    }

    @Override
    public BaseEntity<String> updateUser(AdminUserDTO user) {
        return null;
    }

    @Override
    public BaseEntity<String> getUsers() {
        return null;
    }

    @Override
    public BaseEntity<String> getAdmins() {
        return null;
    }

    @Override
    public BaseEntity<String> addSession(AdminMovieSessionDTO session) {
        return null;
    }

    @Override
    public BaseEntity<String> deleteSession(Long id) {
        return null;
    }

    @Override
    public BaseEntity<String> updateSession(AdminMovieSessionDTO session) {
        return null;
    }

    @Override
    public BaseEntity<String> changePermission(ChangePermissionRequest request) {
        return null;
    }

}
