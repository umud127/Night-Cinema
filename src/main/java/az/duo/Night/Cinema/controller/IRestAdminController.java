package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.User;

import java.util.List;

public interface IRestAdminController {

    BaseEntity<String> addMovie(AdminMovieDTO movie);

    BaseEntity<String> deleteMovie(Long id);

    BaseEntity<String> updateMovie(Long movieId, AdminMovieDTO movie);


    BaseEntity<String> deleteUser(Long id);


    BaseEntity<List<User>> getUsers();

    BaseEntity<String> makeAdmin(String username);

    BaseEntity<List<User>> getAdmins();


    BaseEntity<String> addSession(AdminMovieSessionDTO session);

    BaseEntity<String> deleteSession(Long id);

    BaseEntity<String> updateSession(AdminMovieSessionDTO session);


    BaseEntity<String> changePermission(ChangePermissionRequest request);
}
