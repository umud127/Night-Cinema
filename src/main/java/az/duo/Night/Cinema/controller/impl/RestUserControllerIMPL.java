package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestUserController;
import az.duo.Night.Cinema.dto.user.*;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.enums.RoleName;
import az.duo.Night.Cinema.service.IRestUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RestUserControllerIMPL implements IRestUserController {

    private final IRestUserService restUserService;

    @GetMapping(path = "/check")
    @Override
    public BaseEntity<RoleName> checkUserRole(@RequestHeader("Authorization") String token) {
        return restUserService.checkUserRole(token.substring(7));
    }

    @GetMapping(path = "/info")
    @Override
    public BaseEntity<DTOUserInfo> getUserInfo(@RequestHeader("Authorization") String token) {
        return restUserService.getUserInfo(token.substring(7));
    }

    @GetMapping(path = "/movie")
    @Override
    public BaseEntity<DTOUserMovie> getUserMovie(@RequestHeader("Authorization") String token) {
        return restUserService.getUserMovie(token.substring(7));
    }

    @PutMapping(path = "/updateInfos")
    @Override
    public BaseEntity<String> updateUserInfos(@RequestHeader("Authorization") String token, @Valid @RequestBody DTOUserIU user) {
        return restUserService.updateUserInfos(token.substring(7), user);
    }

    @PutMapping(path = "/updatePassword")
    @Override
    public BaseEntity<String> updateUserPassword(@RequestHeader("Authorization") String token, @Valid @RequestBody DTOUserPassword password) {
        return restUserService.updateUserPassword(token.substring(7), password);
    }

    @PutMapping(path = "/updateProfilePhoto", consumes = "multipart/form-data")
    @Override
    public BaseEntity<String> updateUserProfilePhoto(@RequestHeader("Authorization") String token, @RequestPart("photo") MultipartFile photo) {
        return restUserService.updateUserProfilePhoto(token.substring(7), photo);
    }
}
