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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/admin")
@RequiredArgsConstructor
public class RestAdminControllerIMPL implements IRestAdminController {

    private final IRestAdminService restAdminService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addMovie")
    public BaseEntity<String> addMovie(String token, AdminMovieDTO movie) {
        return restAdminService.addMovie(token, movie);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteMovie")
    public BaseEntity<String> deleteMovie(String token, Long id) {
        return restAdminService.deleteMovie(token, id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateMovie")
    public BaseEntity<String> updateMovie(String token, AdminMovieDTO movie) {
        return restAdminService.updateMovie(token, movie);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public BaseEntity<String> addUser(String token, AdminUserDTO user) {
        return restAdminService.addUser(token, user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser")
    public BaseEntity<String> deleteUser(String token, Long id) {
        return restAdminService.deleteUser(token, id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateUser")
    public BaseEntity<String> updateUser(String token, AdminUserDTO user) {
        return restAdminService.updateUser(token, user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUsers")
    public BaseEntity<String> getUsers(String token) {
        return restAdminService.getUsers(token);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAdmins")
    public BaseEntity<String> getAdmins(String token) {
        return restAdminService.getAdmins(token);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addSession")
    public BaseEntity<String> addSession(String token, AdminMovieSessionDTO session) {
        return restAdminService.addSession(token, session);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteSession")
    public BaseEntity<String> deleteSession(String token, Long id) {
        return restAdminService.deleteSession(token, id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateSession")
    public BaseEntity<String> updateSession(String token, AdminMovieSessionDTO session) {
        return restAdminService.updateSession(token, session);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/changePermission")
    public BaseEntity<String> changePermission(String token, ChangePermissionRequest request) {
        return restAdminService.changePermission(token, request);
    }
}
