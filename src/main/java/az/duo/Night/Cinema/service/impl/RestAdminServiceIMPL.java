package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.AdminUserDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.Movie;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.exception.BadRequestException;
import az.duo.Night.Cinema.repository.RestMovieRepo;
import az.duo.Night.Cinema.service.IRestAdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestAdminServiceIMPL implements IRestAdminService {

    private final RestMovieRepo restMovieRepo;

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
            throw new BadRequestException("Some of the fields are empty", "/admin/addMovie");
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
        if (id == null) {
            throw new BadRequestException("Movie id is empty", "/admin/deleteMovie");
        }

        if (!restMovieRepo.existsById(id)) {
            throw new BadRequestException("Movie not found", "/admin/deleteMovie");
        }

        restMovieRepo.deleteById(id);
        return BaseEntity.ok("Movie was deleted successfully");
    }

    @Override
    @Transactional
    public BaseEntity<String> updateMovie(AdminMovieDTO movie) {
        return null;
    }

    @Override
    public BaseEntity<String> deleteUser(Long id) {
        return null;
    }

    @Override
    @Transactional
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
    @Transactional
    public BaseEntity<String> addSession(AdminMovieSessionDTO session) {
        return null;
    }

    @Override
    public BaseEntity<String> deleteSession(Long id) {
        return null;
    }

    @Override
    @Transactional
    public BaseEntity<String> updateSession(AdminMovieSessionDTO session) {
        return null;
    }

    @Override
    @Transactional
    public BaseEntity<String> changePermission(ChangePermissionRequest request) {
        return null;
    }

}
