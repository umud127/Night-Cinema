package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.AdminUserDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;

public interface IRestAdminController {

    BaseEntity<String> addMovie(AdminMovieDTO movie);

    BaseEntity<String> deleteMovie(Long id);

    BaseEntity<String> updateMovie(AdminMovieDTO movie);


    BaseEntity<String> addUser(AdminUserDTO user);

    BaseEntity<String> deleteUser(Long id);

    BaseEntity<String> updateUser(AdminUserDTO user);


    BaseEntity<String> getUsers();

    BaseEntity<String> getAdmins();


    BaseEntity<String> addSession(AdminMovieSessionDTO session);

    BaseEntity<String> deleteSession(Long id);

    BaseEntity<String> updateSession(AdminMovieSessionDTO session);


    BaseEntity<String> changePermission(ChangePermissionRequest request);
}
