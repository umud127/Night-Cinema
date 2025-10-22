package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.AdminUserDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.Permission;

import java.util.List;

public interface IRestAdminController {

    BaseEntity<String> addMovie(String token, AdminMovieDTO movie);

    BaseEntity<String> deleteMovie(String token, Long id);

    BaseEntity<String> updateMovie(String token, AdminMovieDTO movie);


    BaseEntity<String> addUser(String token, AdminUserDTO user);

    BaseEntity<String> deleteUser(String token, Long id);

    BaseEntity<String> updateUser(String token, AdminUserDTO user);


    BaseEntity<String> addSession(String token, AdminMovieSessionDTO session);

    BaseEntity<String> deleteSession(String token, Long id);

    BaseEntity<String> updateSession(String token, AdminMovieSessionDTO session);


    BaseEntity<String> changePermission(String token, ChangePermissionRequest request);
}
