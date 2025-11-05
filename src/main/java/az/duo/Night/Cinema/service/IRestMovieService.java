package az.duo.Night.Cinema.service;

import az.duo.Night.Cinema.dto.movie.DTOMovie;
import az.duo.Night.Cinema.dto.movie.DTOMovie3;
import az.duo.Night.Cinema.entity.BaseEntity;

import java.util.List;

public interface IRestMovieService {

    BaseEntity<List<DTOMovie>> getStarMovie();

    BaseEntity<List<DTOMovie3>> getAllMovies();

    BaseEntity<List<DTOMovie3>> getMoviesByName(String language);
}
