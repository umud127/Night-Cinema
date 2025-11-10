package az.duo.Night.Cinema.service;

import az.duo.Night.Cinema.dto.movie.DTOStarMovie;
import az.duo.Night.Cinema.dto.movie.DTOMovieAll;
import az.duo.Night.Cinema.entity.BaseEntity;

import java.util.List;

public interface IRestMovieService {

    BaseEntity<List<DTOStarMovie>> getStarMovie();

    BaseEntity<List<DTOMovieAll>> getAllMovies();

    BaseEntity<List<DTOMovieAll>> getMoviesByName(String language);
}
