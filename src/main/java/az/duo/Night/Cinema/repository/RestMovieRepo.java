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

    @Query(value = """
SELECT 
    m.name,
    m.description,
    m.cover_photo_url AS coverPhotoUrl,
    m.genres,
    m.release_date AS releaseDate,
    m.movie_duration AS movieDuration
FROM cinema_movie m
WHERE LOWER(m.name) LIKE LOWER(CONCAT(:prefix, '%'))
""", nativeQuery = true)
    List<DTOMovie3> findAllByNameStartingWith(@Param("prefix") String prefix);

    @Query(value = """
SELECT 
    m.name,
    m.description,
    m.background_img_url AS backgroundImgUrl,
    m.genres,
    m.release_date AS releaseDate,
    m.movie_duration AS movieDuration
FROM cinema_movie m
WHERE m.star_movie = true
""", nativeQuery = true)
    List<DTOMovie> findStarMovie();

    @Query(value = """
SELECT
    m.id,
    m.name,
    m.description,
    m.cover_photo_url AS "coverPhotoUrl",
    string_agg(DISTINCT g.genre, ',') AS "genresString",
    m.release_date AS "releaseDate",
    m.movie_duration AS "movieDuration",
    string_agg(DISTINCT ma.actor, ',') AS "actorsString",
    m.director,
    m.star_movie AS starMovie,
    m.trailer_url AS trailerUrl,
    m.background_img_url AS bacgroundImgUrl
FROM cinema_movie m
LEFT JOIN movie_genres g ON m.id = g.movie_id
LEFT JOIN movie_actors ma ON m.id = ma.movie_id
GROUP BY m.id;
""", nativeQuery = true)
    List<DTOMovie3> findAllMovies();
}
