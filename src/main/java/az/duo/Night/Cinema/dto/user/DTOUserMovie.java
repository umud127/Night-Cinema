package az.duo.Night.Cinema.dto.user;

import az.duo.Night.Cinema.dto.movie.DTOMovie;
import lombok.Data;

import java.util.List;

@Data
public class DTOUserMovie {

    private int gotMovies = 0;
    private List<DTOMovie> movies;
}
