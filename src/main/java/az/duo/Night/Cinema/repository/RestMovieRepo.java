package az.duo.Night.Cinema.repository;

import az.duo.Night.Cinema.dto.movie.DTOMovie;
import az.duo.Night.Cinema.dto.movie.DTOMovie3;
import az.duo.Night.Cinema.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestMovieRepo extends JpaRepository<Movie, Long> {

    @Query("SELECT new az.duo.Night.Cinema.dto.movie.DTOMovie3(" +
            "m.name, m.description, m.coverPhotoUrl, m.genres, m.releaseDate, m.movieDuration) " +
            "FROM Movie m " +
            "WHERE LOWER(m.name) LIKE :prefix")
    List<DTOMovie3> findAllByNameStartingWith(@Param("prefix") String prefix);

    @Query("SELECT new az.duo.Night.Cinema.dto.movie.DTOMovie(" +
            "m.name, m.description, m.backgroundImgUrl, m.genres, m.releaseDate, m.movieDuration) " +
            "FROM Movie m WHERE m.starMovie = true")
    List<DTOMovie> findStarMovie();

    @Query("SELECT new az.duo.Night.Cinema.dto.movie.DTOMovie3(" +
            "m.name, m.description, m.coverPhotoUrl, m.genres, m.releaseDate, m.movieDuration) " +
            "FROM Movie m")
    List<DTOMovie3> findAllMovies();
}
