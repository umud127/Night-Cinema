package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.dto.admin.AdminMovieDTO;
import az.duo.Night.Cinema.dto.admin.AdminMovieSessionDTO;
import az.duo.Night.Cinema.dto.admin.ChangePermissionRequest;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.Permission;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRestAdminController {

    BaseEntity<String> addMovie(MultipartFile cover, MultipartFile back, String movie)  throws JsonProcessingException;

    BaseEntity<String> deleteMovie(Long id);

    BaseEntity<String> updateMovie(Long movieId, MultipartFile cover, MultipartFile back, String movie) throws JsonProcessingException;


    BaseEntity<String> deleteUser(Long id);


    BaseEntity<List<User>> getUsers();

    BaseEntity<String> makeAdmin(String username);

    BaseEntity<List<User>> getAdmins();

    BaseEntity<String> makeAdminUser(String username);


    BaseEntity<String> addSession(AdminMovieSessionDTO session);

    BaseEntity<String> deleteSession(Long id);

    BaseEntity<String> updateSession(AdminMovieSessionDTO session);


    BaseEntity<List<Permission>> getPermissions(String token);

    BaseEntity<String> changePermission(ChangePermissionRequest request);
}
