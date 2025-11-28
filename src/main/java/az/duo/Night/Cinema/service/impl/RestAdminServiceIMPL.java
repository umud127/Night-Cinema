package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.Movie;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.Permission;
import az.duo.Night.Cinema.enums.RoleName;
import az.duo.Night.Cinema.exception.BadRequestException;
import az.duo.Night.Cinema.exception.NotFoundException;
import az.duo.Night.Cinema.jwt.JWTService;
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
    private final JWTService jWTService;

    @Override
    @Transactional
    public BaseEntity<String> addMovie(AdminMovieDTO movie) {
        if (movie.getName() == null) {
            throw new BadRequestException("Movie name is empty", "/admin/addMovie");
        }
        if (movie.getDescription() == null) {
            throw new BadRequestException("Movie description is empty", "/admin/addMovie");
        }

        if (movie.getCoverPhotoUrl() == null) {
            throw new BadRequestException("Movie cover photo url is empty", "/admin/addMovie");
        }
        if (movie.getBackgroundImgUrl() == null) {
            throw new BadRequestException("Movie background image url is empty", "/admin/addMovie");
        }

        if (movie.getMovieDuration() == null) {
            throw new BadRequestException("Movie duration is empty", "/admin/addMovie");
        }
        if (movie.getGenre() == null) {
            throw new BadRequestException("Movie genre is empty", "/admin/addMovie");
        }

        if (movie.getDirector() == null) {
            throw new BadRequestException("Movie director is empty", "/admin/addMovie");
        }
        if (movie.getActors() == null) {
            throw new BadRequestException("Movie actors is empty", "/admin/addMovie");
        }

        if (movie.getReleaseDate() == null) {
            throw new BadRequestException("Movie release date is empty", "/admin/addMovie");
        }

        if (movie.getTrailerUrl() == null) {
            throw new BadRequestException("Movie trailer url is empty", "/admin/addMovie");
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

        if (movie.isStarMovie()) {
            newMovie.setStarMovie(true);
            newMovie.setBackgroundImgUrl(movie.getBackgroundImgUrl());
        } else {
            newMovie.setStarMovie(false);
            newMovie.setBackgroundImgUrl(null);
        }

        restMovieRepo.save(newMovie);
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

        restMovieRepo.save(updatedMovie);
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
        List<User> users = restUserRepo.findAllUsers();
        return BaseEntity.ok(users);
    }


    @Override
    public BaseEntity<String> makeAdmin(String username) {
        Optional<User> user = restUserRepo.findUserByUsername(username);
        User updatedUser = user.get();

        if (!user.isPresent()) {
            throw new NotFoundException("User not found", "/admin/makeAdmin");
        }

        if (updatedUser.getRole().equals("ADMIN")) {
            return BaseEntity.ok("User is already admin");
        }

        updatedUser.setRole(RoleName.ADMIN);
        restUserRepo.save(updatedUser);

        return BaseEntity.ok("User (" + username + ") was made admin successfully");
    }

    @Override
    public BaseEntity<List<User>> getAdmins() {
        List<User> admins = restUserRepo.findAllAdmins();
        return BaseEntity.ok(admins);
    }

    @Override
    @Transactional
    public BaseEntity<String> makeAdminUser(String username) {
        User admin = restUserRepo.findAdminByUsername(username)
                .orElseThrow(()-> new NotFoundException("Admin not found", "/admin/makeAdminUser"));

        admin.setRole(RoleName.USER);

        restUserRepo.save(admin);
        return BaseEntity.ok("Admin (" + username + ") was made user successfully");
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
    public BaseEntity<List<Permission>> getPermissions(String token) {
        Long id = jWTService.extractIdFromToken(token);

        if(id == null) {
            throw new BadRequestException("Invalid token", "/admin/changePermission");
        }

        List<Permission> permissions = restUserRepo.findAdminPermissions(id);

        return BaseEntity.ok(permissions);
    }

    @Override
    @Transactional
    public BaseEntity<String> changePermission(ChangePermissionRequest request) {
        User user = restUserRepo.findAdminByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Admin not found", "/admin/changePermission") );

        user.setAdminPermissions(request.getPermissions());

        restUserRepo.save(user);

        return BaseEntity.ok("Permission was changed successfully");
    }

}
