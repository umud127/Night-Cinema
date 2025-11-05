package az.duo.Night.Cinema.dto.movie;

import az.duo.Night.Cinema.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class DTOMovie {

    private String name;

    private String description;

    private String backgroundImgUrl;

    private List<Genre> genres;

    private LocalDate releaseDate;

    private String movieDuration;
}
