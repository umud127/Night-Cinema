package az.duo.Night.Cinema.dto.movie;

import az.duo.Night.Cinema.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOMovie {

    private String name;

    private String description;

    private String backgroundImgUrl;

    private List<Genre> genres;

    private LocalDate releaseDate;

    private Long movieDuration;
}
