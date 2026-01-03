package az.duo.Night.Cinema.repository;

import az.duo.Night.Cinema.entity.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestSessionRepo extends JpaRepository<MovieSession, Long> {

    List<MovieSession> findMovieSessionsByMovie_Name(String movieName);
}
