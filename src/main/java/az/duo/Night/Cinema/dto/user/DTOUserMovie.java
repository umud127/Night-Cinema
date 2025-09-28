package az.duo.Night.Cinema.dto.user;

import az.duo.Night.Cinema.dto.movie.DTOMovie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DTOUserMovie {

    private int gotMovies = 0;
    private List<DTOMovie> movies;
}
