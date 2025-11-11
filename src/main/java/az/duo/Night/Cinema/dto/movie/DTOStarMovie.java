package az.duo.Night.Cinema.dto.movie;

import az.duo.Night.Cinema.enums.Genre;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DTOStarMovie {

    public DTOStarMovie(String name,
                        String description,
                        String backgroundImgUrl,
                        String genres,
                        LocalDate releaseDate,
                        String movieDuration) {
        this.name = name;
        this.description = description;
        this.backgroundImgUrl = backgroundImgUrl;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.movieDuration = movieDuration;
    }

//"adventure,action,thriller"
    private String name;

    private String description;

    private String backgroundImgUrl;

    private String genres;

    private LocalDate releaseDate;

    private String movieDuration;
}
