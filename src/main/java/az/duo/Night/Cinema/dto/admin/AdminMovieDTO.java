package az.duo.Night.Cinema.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminMovieDTO {

    String name;
    String description;
    String coverPhotoUrl;

    Long movieDuration;
    String genre;

    String director;
    List<String> actors;

    LocalDate releaseDate;
    String trailerUrl;
}
