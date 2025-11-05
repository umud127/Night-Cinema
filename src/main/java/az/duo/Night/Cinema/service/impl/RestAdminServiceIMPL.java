package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.Movie;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.exception.BadRequestException;
import az.duo.Night.Cinema.exception.NotFoundException;
import az.duo.Night.Cinema.repository.RestMovieRepo;
import az.duo.Night.Cinema.repository.RestUserRepo;
import az.duo.Night.Cinema.service.IRestAdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestAdminServiceIMPL implements IRestAdminService {

    private final RestMovieRepo restMovieRepo;
    private final RestUserRepo restUserRepo;

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
    public BaseEntity<String> updateMovie(Long movieId, AdminMovieDTO movie) {
        if (!restMovieRepo.existsById(movieId)) {
            throw new NotFoundException("Movie not found", "/admin/updateMovie");
        }

        Optional<Movie> dbMovie = restMovieRepo.findById(movieId);
        Movie updatedMovie = dbMovie.get();

        updatedMovie.setName(movie.getName());
        updatedMovie.setDescription(movie.getDescription());

        updatedMovie.setCoverPhotoUrl(movie.getCoverPhotoUrl());
        updatedMovie.setBackgroundImgUrl(movie.getBackgroundImgUrl());

        updatedMovie.setMovieDuration(movie.getMovieDuration());
        updatedMovie.setGenres(movie.getGenre());

        updatedMovie.setDirector(movie.getDirector());
        updatedMovie.setActors(movie.getActors());

        updatedMovie.setReleaseDate(movie.getReleaseDate());
        updatedMovie.setTrailerUrl(movie.getTrailerUrl());

        updatedMovie.setStarMovie(movie.isStarMovie());

        return BaseEntity.ok("Movie was updated successfully");
    }

    @Override
    public BaseEntity<String> deleteUser(Long id) {
        if (id == null) {
            throw new BadRequestException("User id is empty", "/admin/deleteUser");
        }

        if (!restUserRepo.existsById(id)) {
            throw new NotFoundException("User not found", "/admin/deleteUser");
        }

        restUserRepo.deleteById(id);

        return BaseEntity.ok("User was deleted successfully");
    }

    @Override
    public BaseEntity<List<User>> getUsers() {
        List<User> users = restUserRepo.findAll();
        return null;
    }

    @Override
    public BaseEntity<List<User>> getAdmins() {
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
