package az.duo.Night.Cinema.dto.movie;

import az.duo.Night.Cinema.enums.Genre;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DTOMovie3 {


    public DTOMovie3(String name, String description, String coverPhotoUrl, List<Genre> genres, LocalDate releaseDate, String movieDuration) {
        this.name = name;
        this.description = description;
        this.coverPhotoUrl = coverPhotoUrl;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.movieDuration = movieDuration;
    }

    private String name;
    private String description;
    private String coverPhotoUrl;
    private List<Genre> genres;
    private LocalDate releaseDate;
    private String movieDuration;
}
