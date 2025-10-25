package az.duo.Night.Cinema.dto.admin;

import az.duo.Night.Cinema.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
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
    List<Genre> genre;

    String director;
    List<String> actors;

    LocalDate releaseDate;
    String trailerUrl;

    boolean starMovie;
}
