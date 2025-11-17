package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestUserController;
import az.duo.Night.Cinema.dto.user.DTOUserIU;
import az.duo.Night.Cinema.dto.user.DTOUserInfo;
import az.duo.Night.Cinema.dto.user.DTOUserMovie;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.enums.RoleName;
import az.duo.Night.Cinema.service.IRestUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RestUserControllerIMPL implements IRestUserController {

    private final IRestUserService restUserService;

    @GetMapping(path = "/check")
    @Override
    public BaseEntity<RoleName> checkUserRole(@RequestHeader("Authorization") String token) {
        return restUserService.checkUserRole(token);
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

    @PutMapping(path = "/update")
    public BaseEntity<String> updateUser(@RequestHeader("Authorization") String token, @Valid @RequestBody DTOUserIU user) {
        return restUserService.updateUser(token.substring(7), user);
    }
}
