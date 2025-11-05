package az.duo.Night.Cinema.service;

import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.User;

import java.util.List;

public interface IRestAdminService {

    BaseEntity<String> addMovie(AdminMovieDTO movie);

    BaseEntity<String> deleteMovie(Long id);

    BaseEntity<String> updateMovie(Long movieId, AdminMovieDTO movie);


    BaseEntity<String> deleteUser(Long id);


    BaseEntity<List<User>> getUsers();

    BaseEntity<List<User>> getAdmins();


    BaseEntity<String> addSession(AdminMovieSessionDTO session);

    BaseEntity<String> deleteSession(Long id);

    BaseEntity<String> updateSession(AdminMovieSessionDTO session);


    BaseEntity<String> changePermission(ChangePermissionRequest request);
}
