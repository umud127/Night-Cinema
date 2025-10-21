package az.duo.Night.Cinema.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminMovieDTO {

    String name;
    String description;
    String coverPhotoUrl;
    Long movieDuration;
    String language;
    String genre;
    String director;
    String actors;
    String releaseDate;
    String rating;
    String trailerUrl;
}
