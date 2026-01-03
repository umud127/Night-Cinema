package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.dto.session.DTOMovieSession;
import az.duo.Night.Cinema.entity.BaseEntity;

import java.util.List;

public interface IRestSessionController {

    BaseEntity<List<DTOMovieSession>> getMovieSessionsByName(String name);

    BaseEntity<List<DTOMovieSession>> getMovieSessions();
}
