package az.duo.Night.Cinema.dto;

import az.duo.Night.Cinema.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class DTOUserMovie {

    private int gotMovies = 0;
    private List<Movie> movies;
}
