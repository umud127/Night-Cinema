package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.movie.DTOMovie2;
import az.duo.Night.Cinema.dto.session.DTOMovieSession;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.MovieSession;
import az.duo.Night.Cinema.repository.RestSessionRepo;
import az.duo.Night.Cinema.service.IRestSessionService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestSessionServiceIMPL implements IRestSessionService {

    private final RestSessionRepo restSessionRepo;

    @Override
    public BaseEntity<List<DTOMovieSession>> getMovieSessionsByName(String name) {
        List<MovieSession> sessions = restSessionRepo.findMovieSessionsByMovie_Name(name);

        return BaseEntity.ok(getDtoMovieSessions(sessions));
    }

    @Override
    public BaseEntity<List<DTOMovieSession>> getMovieSessions() {
        List<MovieSession> sessions = restSessionRepo.findAll();

        return BaseEntity.ok(getDtoMovieSessions(sessions));
    }

    @NonNull
    private List<DTOMovieSession> getDtoMovieSessions(List<MovieSession> sessions) {
        List<DTOMovieSession> dtoMovieSessions = new ArrayList<>();

        sessions.forEach(session -> {
            DTOMovieSession dtoMovieSession = new DTOMovieSession();

            dtoMovieSession.setMovie(new DTOMovie2(
                    session.getMovie().getName(),
                    session.getMovie().getDescription(),
                    session.getMovie().getCoverPhotoUrl(),
                    session.getMovie().getMovieDuration(),
                    session.getStartTime()
                    ));

            dtoMovieSession.setRoom(session.getRoom());
            dtoMovieSession.setDubbed(session.isDubbed());
            dtoMovieSession.setLocation(session.getLocation());
            dtoMovieSession.setLanguage(session.getLanguage());
            dtoMovieSession.setSubbed(!session.isDubbed());

            dtoMovieSessions.add(dtoMovieSession);
        });
        return dtoMovieSessions;
    }
}
