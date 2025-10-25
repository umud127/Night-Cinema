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
    public BaseEntity<String> addMovie(AdminMovieDTO movie) {

        return BaseEntity.ok("Movie was added");
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
    public BaseEntity<String> addUser(AdminUserDTO user) {
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
