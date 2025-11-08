package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.movie.DTOMovie;
import az.duo.Night.Cinema.dto.movie.DTOMovie3;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.repository.RestMovieRepo;
import az.duo.Night.Cinema.service.IRestMovieService;
import az.duo.Night.Cinema.util.GenreListConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestMovieServiceIMPL implements IRestMovieService {

    private final RestMovieRepo restMovieRepo;
    private final GenreListConverter genreListConverter;

    @Override
    public BaseEntity<List<DTOMovie>> getStarMovie() {
        try {
            List<DTOMovie> starMovie = restMovieRepo.findStarMovie();

            if (starMovie != null) {
                return BaseEntity.ok(starMovie);
            } else {
                log.warn("No Star Movie Found");
                return BaseEntity.notOk(StatusCode.NOT_FOUND, "No Star Movie Found", "/movie/star");
            }
        } catch (Exception e) {
            log.error("Error fetching star movie: {}", e.getMessage(), e);
            return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, "Internal Server Error", "/movie/star");
        }
    }

    @Override
    public BaseEntity<List<DTOMovie3>> getAllMovies() {
            List<DTOMovie3> dbMovies = restMovieRepo.findAllMovies();

            if(dbMovies != null && !dbMovies.isEmpty()) {
                return BaseEntity.ok(dbMovies.stream()
                        .map(m -> new DTOMovie3(
                                m.getName(),
                                m.getDescription(),
                                m.getCoverPhotoUrl(),
                                genreListConverter.convertToEntityAttribute(m.getGenreString()),
                                m.getReleaseDate(),
                                m.getMovieDuration(),
                                m.getActors(),
                                m.getDirector()
                        ))
                        .collect(Collectors.toList()));
            }

            return BaseEntity.notOk(StatusCode.NOT_FOUND, "No Movies Found", "/movie/all");
    }

    @Override
    public BaseEntity<List<DTOMovie3>> getMoviesByName(String movieName) {
        try {
            // Parametrə % əlavə edirik ki, LIKE işləsin
            String searchPattern = movieName.toLowerCase() + "%";

            List<DTOMovie3> dbMovies = restMovieRepo.findAllByNameStartingWith(searchPattern);

            if(dbMovies != null && !dbMovies.isEmpty()) {
                return BaseEntity.ok(dbMovies);
            } else {
                return BaseEntity.notOk(StatusCode.NOT_FOUND, "No Movies Found", "/movie/by_name");
            }
        } catch (Exception e) {
            log.error("Error fetching movies with name: {}", e.getMessage(), e);
            return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, "Internal Server Error", "/movie/by_name");
        }
    }
}
