package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestUserController;
import az.duo.Night.Cinema.dto.DTOUser;
import az.duo.Night.Cinema.dto.DTOUserIU;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.service.IRestUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RestUserControllerIMPL implements IRestUserController {

    private final IRestUserService restUserService;

    @GetMapping(path = "/me")
    public BaseEntity<DTOUser> getUser(@RequestHeader("Authorization") String token) {
        return restUserService.getUser(token.substring(7));
    }

    @PutMapping(path = "/update")
    public BaseEntity<DTOUser> updateUser(@RequestHeader("Authorization") String token, @RequestBody DTOUserIU user) {
        return restUserService.updateUser(token.substring(7), user);
    }
}
