package az.duo.Night.Cinema.service;

import az.duo.Night.Cinema.dto.session.DTOMovieSession;
import az.duo.Night.Cinema.entity.BaseEntity;

import java.util.List;

public interface IRestSessionService {

    BaseEntity<List<DTOMovieSession>> getMovieSessionsByName(String name);

    BaseEntity<List<DTOMovieSession>> getMovieSessions();
}
