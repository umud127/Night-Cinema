package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestAdminController;
import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.Permission;
import az.duo.Night.Cinema.service.IRestAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/admin")
@RequiredArgsConstructor
public class RestAdminControllerIMPL implements IRestAdminController {

    private final IRestAdminService restAdminService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/addMovie", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseEntity<String> addMovie(
            @ModelAttribute AdminMovieDTO movie) {
        return restAdminService.addMovie(movie);
    }



    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteMovie/{id}")
    public BaseEntity<String> deleteMovie(@PathVariable Long id) {
        return restAdminService.deleteMovie(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/updateMovie", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseEntity<String> updateMovie(
            @RequestParam("id") Long id,
            @ModelAttribute("movie") AdminMovieDTO movie) {
        return restAdminService.updateMovie(id, movie);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser/{id}")
    public BaseEntity<String> deleteUser(@PathVariable Long id) {
        return restAdminService.deleteUser(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUsers")
    public BaseEntity<List<User>> getUsers() {
        return restAdminService.getUsers();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAdmins")
    public BaseEntity<List<User>> getAdmins() {
        return restAdminService.getAdmins();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/makeAdminUser")
    public BaseEntity<String> makeAdminUser(@RequestParam String username) {
        return restAdminService.makeAdminUser(username);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addSession")
    public BaseEntity<String> addSession(@RequestBody AdminMovieSessionDTO session) {
        return restAdminService.addSession(session);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteSession")
    public BaseEntity<String> deleteSession(@RequestParam Long id) {
        return restAdminService.deleteSession(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateSession")
    public BaseEntity<String> updateSession(@RequestBody AdminMovieSessionDTO session) {
        return restAdminService.updateSession(session);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getPermissions")
    public BaseEntity<List<Permission>> getPermissions(@RequestParam String username) {
        return restAdminService.getPermissions(username);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/changePermission")
    public BaseEntity<String> changePermission(@RequestBody ChangePermissionRequest request) {
        return restAdminService.changePermission(request);
    }
}
