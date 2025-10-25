package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.AdminUserDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.service.IRestAdminService;
import org.springframework.stereotype.Service;

@Service
public class RestAdminServiceIMPL implements IRestAdminService {
    @Override
    public BaseEntity<String> addMovie(String token, AdminMovieDTO movie) {
        return null;
    }

    @Override
    public BaseEntity<String> deleteMovie(String token, Long id) {
        return null;
    }

    @Override
    public BaseEntity<String> updateMovie(String token, AdminMovieDTO movie) {
        return null;
    }

    @Override
    public BaseEntity<String> addUser(String token, AdminUserDTO user) {
        return null;
    }

    @Override
    public BaseEntity<String> deleteUser(String token, Long id) {
        return null;
    }

    @Override
    public BaseEntity<String> updateUser(String token, AdminUserDTO user) {
        return null;
    }

    @Override
    public BaseEntity<String> addSession(String token, AdminMovieSessionDTO session) {
        return null;
    }

    @Override
    public BaseEntity<String> deleteSession(String token, Long id) {
        return null;
    }

    @Override
    public BaseEntity<String> updateSession(String token, AdminMovieSessionDTO session) {
        return null;
    }

    @Override
    public BaseEntity<String> changePermission(String token, ChangePermissionRequest request) {
        return null;
    }
}
