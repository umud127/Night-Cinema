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

//    @Query(value = """
//SELECT
//    m.name,
//    m.description,
//    m.cover_photo_url AS coverPhotoUrl,
//    m.genres,
//    m.release_date AS releaseDate,
//    m.movie_duration AS movieDuration
//FROM cinema_movie m
//""", nativeQuery = true)
//    List<DTOMovie3> findAllMovies();

    @Query(value = """
SELECT 
    m.id as id,
    m.name as name,
    m.description as description,
    m.cover_photo_url as coverPhotoUrl,
    m.release_date as releaseDate,
    m.movie_duration as movieDuration,
    GROUP_CONCAT(distinct g.genre) as genres,
    GROUP_CONCAT(distinct a.actor) as actors
FROM cinema_movie m
LEFT JOIN movie_genres g ON g.movie_id = m.id
LEFT JOIN movie_actors a ON a.movie_id = m.id
GROUP BY m.id, m.name, m.description, m.cover_photo_url, m.release_date, m.movie_duration
""", nativeQuery = true)
    List<DTOMovie3> findAllMovies();

}
