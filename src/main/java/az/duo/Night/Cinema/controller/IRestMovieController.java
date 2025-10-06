package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.dto.movie.DTOMovie;
import az.duo.Night.Cinema.entity.BaseEntity;

import java.util.List;

public interface IRestMovieController {

    BaseEntity<DTOMovie> getStarMovie();

    BaseEntity<List<DTOMovie>> getAllMovies();

    BaseEntity<List<DTOMovie>> getMoviesByName(String language);
}
