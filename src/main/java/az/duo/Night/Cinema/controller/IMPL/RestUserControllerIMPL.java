package az.duo.Night.Cinema.controller.IMPL;

import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.service.RestUserServiceIMPL;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RestUserControllerIMPL {

    private final RestUserServiceIMPL restUserServiceIMPL;

    @GetMapping(path = "/me")
    public User getUser(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        return restUserServiceIMPL.getUser(token.substring(7));
    }
}
