package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestSessionController;
import az.duo.Night.Cinema.dto.session.DTOMovieSession;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.service.IRestSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/session")
@RequiredArgsConstructor
public class RestSessionControllerIMPL implements IRestSessionController {

    private final IRestSessionService restSessionService;


    @Override
    @GetMapping(path = "/getSessionByName")
    public BaseEntity<List<DTOMovieSession>> getMovieSessionsByName(String name) {
        return restSessionService.getMovieSessionsByName(name);
    }

    @Override
    @GetMapping(path = "getSessions")
    public BaseEntity<List<DTOMovieSession>> getMovieSessions() {
        return restSessionService.getMovieSessions();
    }
}
