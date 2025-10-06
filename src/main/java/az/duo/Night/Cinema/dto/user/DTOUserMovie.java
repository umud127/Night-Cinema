package az.duo.Night.Cinema.dto.user;

import az.duo.Night.Cinema.dto.movie.DTOMovie;
import az.duo.Night.Cinema.dto.movie.DTOMovie2;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DTOUserMovie {

    private int gotMovies = 0;
    private List<DTOMovie2> movies;
}
