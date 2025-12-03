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
import az.duo.Night.Cinema.service.IRestCloudinaryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestAdminServiceIMPL implements IRestAdminService {

    private final RestMovieRepo restMovieRepo;
    private final RestUserRepo restUserRepo;
    private final JWTService jWTService;
    private final IRestCloudinaryService restCloudinaryService;

    @Override
    @Transactional
    public BaseEntity<String> addMovie(AdminMovieDTO movie) {
        if (movie.getName() == null) {
            throw new BadRequestException("Movie name is empty", "/admin/addMovie");
        }
        if (movie.getDescription() == null) {
            throw new BadRequestException("Movie description is empty", "/admin/addMovie");
        }

        if (movie.getCoverPhoto() == null) {
            throw new BadRequestException("Movie cover photo url is empty", "/admin/addMovie");
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


        String coverPhotoUrl;
        String backgroundImgUrl;
        try {
            coverPhotoUrl = restCloudinaryService.uploadImage(movie.getCoverPhoto());
        } catch (IOException e) {
            throw new BadRequestException("Movie cover photo upload failed", "/admin/addMovie");
        }

        newMovie.setCoverPhotoUrl(coverPhotoUrl);

        if (movie.isStarMovie()) {
            try {
                backgroundImgUrl = restCloudinaryService.uploadImage(movie.getBackground());
            } catch (IOException e) {
                throw new BadRequestException("Movie background photo upload failed", "/admin/addMovie");
            }
            newMovie.setStarMovie(true);
            newMovie.setBackgroundImgUrl(backgroundImgUrl);
        } else {
            newMovie.setStarMovie(false);
            newMovie.setBackgroundImgUrl(null);
        }


        newMovie.setMovieDuration(movie.getMovieDuration());
        newMovie.setGenres(movie.getGenre());

        newMovie.setDirector(movie.getDirector());
        newMovie.setActors(movie.getActors());

        newMovie.setReleaseDate(movie.getReleaseDate());
        newMovie.setTrailerUrl(movie.getTrailerUrl());

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
        Movie dbMovie = restMovieRepo.findById(movieId)
                .orElseThrow(()->  new NotFoundException("Movie not found", "/admin/updateMovie"));

        dbMovie.setName(movie.getName());
        dbMovie.setDescription(movie.getDescription());

        String coverPhotoUrl;
        String backgroundImgUrl;

        if (movie.getCoverPhoto() != null) {
            try {
                coverPhotoUrl = restCloudinaryService.uploadImage(movie.getCoverPhoto());
                dbMovie.setCoverPhotoUrl(coverPhotoUrl);
            } catch (IOException e) {
                throw new BadRequestException("Cover photo upload failed", "/admin/updateMovie");
            }
        }

        if (movie.getBackground() != null) {
            try {
                backgroundImgUrl = restCloudinaryService.uploadImage(movie.getBackground());
                dbMovie.setBackgroundImgUrl(backgroundImgUrl);
            } catch (IOException e) {
                throw new BadRequestException("Background photo upload failed", "/admin/updateMovie");
            }
        }


        dbMovie.setMovieDuration(movie.getMovieDuration());
        dbMovie.setGenres(movie.getGenre());

        dbMovie.setDirector(movie.getDirector());
        dbMovie.setActors(movie.getActors());

        dbMovie.setReleaseDate(movie.getReleaseDate());
        dbMovie.setTrailerUrl(movie.getTrailerUrl());

        dbMovie.setStarMovie(movie.isStarMovie());

        restMovieRepo.save(dbMovie);
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
    public BaseEntity<List<Permission>> getPermissions(String username) {
        List<Permission> permissions = restUserRepo.findAdminPermissions(username);

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
