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
    public BaseEntity<String> addMovie(AdminMovieDTO movie) {
        return restAdminService.addMovie(movie);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteMovie")
    public BaseEntity<String> deleteMovie(Long id) {
        return restAdminService.deleteMovie(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateMovie")
    public BaseEntity<String> updateMovie(AdminMovieDTO movie) {
        return restAdminService.updateMovie(movie);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public BaseEntity<String> addUser(AdminUserDTO user) {
        return restAdminService.addUser(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser")
    public BaseEntity<String> deleteUser(Long id) {
        return restAdminService.deleteUser(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateUser")
    public BaseEntity<String> updateUser(AdminUserDTO user) {
        return restAdminService.updateUser(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUsers")
    public BaseEntity<String> getUsers() {
        return restAdminService.getUsers();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAdmins")
    public BaseEntity<String> getAdmins() {
        return restAdminService.getAdmins();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addSession")
    public BaseEntity<String> addSession(AdminMovieSessionDTO session) {
        return restAdminService.addSession(session);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteSession")
    public BaseEntity<String> deleteSession(Long id) {
        return restAdminService.deleteSession(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateSession")
    public BaseEntity<String> updateSession(AdminMovieSessionDTO session) {
        return restAdminService.updateSession( session);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/changePermission")
    public BaseEntity<String> changePermission(ChangePermissionRequest request) {
        return restAdminService.changePermission(request);
    }
}
