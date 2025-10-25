package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestAdminController;
import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.AdminUserDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.service.IRestAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/admin")
@RequiredArgsConstructor
public class RestAdminControllerIMPL implements IRestAdminController {

    private final IRestAdminService restAdminService;


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addMovie")
    public BaseEntity<String> addMovie(String token, AdminMovieDTO movie) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteMovie")
    public BaseEntity<String> deleteMovie(String token, Long id) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/updateMovie")
    public BaseEntity<String> updateMovie(String token, AdminMovieDTO movie) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public BaseEntity<String> addUser(String token, AdminUserDTO user) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteUser")
    public BaseEntity<String> deleteUser(String token, Long id) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/updateUser")
    public BaseEntity<String> updateUser(String token, AdminUserDTO user) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/getUsers")
    public BaseEntity<String> getUsers(String token) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/getAdmins")
    public BaseEntity<String> getAdmins(String token) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addSession")
    public BaseEntity<String> addSession(String token, AdminMovieSessionDTO session) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteSession")
    public BaseEntity<String> deleteSession(String token, Long id) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/updateSession")
    public BaseEntity<String> updateSession(String token, AdminMovieSessionDTO session) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/changePermission")
    public BaseEntity<String> changePermission(String token, ChangePermissionRequest request) {
        return null;
    }
}
