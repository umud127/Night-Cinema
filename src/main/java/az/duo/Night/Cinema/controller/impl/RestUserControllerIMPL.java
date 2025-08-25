package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestUserController;
import az.duo.Night.Cinema.dto.DTOUserIU;
import az.duo.Night.Cinema.dto.DTOUserInfo;
import az.duo.Night.Cinema.dto.DTOUserMovie;
import az.duo.Night.Cinema.dto.DTOUserSecurity;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.service.IRestUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RestUserControllerIMPL implements IRestUserController {

    private final IRestUserService restUserService;

    @GetMapping(path = "/info")
    @Override
    public BaseEntity<DTOUserInfo> getUserInfo(@RequestHeader("Authorization") String token) {
        return restUserService.getUserInfo(token);
    }

    @GetMapping(path = "/security")
    @Override
    public BaseEntity<DTOUserSecurity> getUserSecurity(@RequestHeader("Authorization") String token) {
        return restUserService.getUserSecurity(token);
    }

    @GetMapping(path = "/movie")
    @Override
    public BaseEntity<DTOUserMovie> getUserMovie(@RequestHeader("Authorization") String token) {
        return restUserService.getUserMovie(token);
    }

    @PutMapping(path = "/update")
    public BaseEntity<DTOUserSecurity> updateUser(@RequestHeader("Authorization") String token, @RequestBody DTOUserIU user) {
        return restUserService.updateUser(token.substring(7), user);
    }
}
