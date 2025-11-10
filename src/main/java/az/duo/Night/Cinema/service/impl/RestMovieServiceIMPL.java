package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.movie.DTOStarMovie;
import az.duo.Night.Cinema.dto.movie.DTOMovieAll;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.repository.RestMovieRepo;
import az.duo.Night.Cinema.service.IRestMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestMovieServiceIMPL implements IRestMovieService {

    private final RestMovieRepo restMovieRepo;

    @Override
    public BaseEntity<List<DTOStarMovie>> getStarMovie() {
        List<DTOStarMovie> starMovie = restMovieRepo.findStarMovie();

        if (starMovie != null) {
            return BaseEntity.ok(starMovie);
        } else {
            return BaseEntity.notOk(StatusCode.NOT_FOUND, "No Star Movie Found", "/movie/star");
        }
    }

    @Override
    public BaseEntity<List<DTOMovieAll>> getAllMovies() {
            List<DTOMovieAll> dbMovies = restMovieRepo.findAllMovies();

            if(dbMovies != null && !dbMovies.isEmpty()) {
                return BaseEntity.ok(dbMovies);
            }

            return BaseEntity.notOk(StatusCode.NOT_FOUND, "No Movies Found", "/movie/all");
    }

    @Override
    public BaseEntity<List<DTOMovieAll>> getMoviesByName(String movieName) {
            // Parametrə % əlavə edirik ki, LIKE işləsin
            String searchPattern = movieName.toLowerCase() + "%";

            List<DTOMovieAll> dbMovies = restMovieRepo.findAllByNameStartingWith(searchPattern);

            if(dbMovies != null && !dbMovies.isEmpty()) {
                return BaseEntity.ok(dbMovies);
            } else {
                return BaseEntity.notOk(StatusCode.NOT_FOUND, "No Movies Found", "/movie/by_name");
            }
    }
}