package az.duo.Night.Cinema.dto.movie;

import az.duo.Night.Cinema.enums.Genre;
import lombok.Data;

import java.time.LocalDate;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DTOMovie3 {


    public DTOMovie3(Long id,
                     String name,
                     String description,
                     String coverPhotoUrl,
                     String genreString,
                     Date releaseDate,
                     String movieDuration,
                     String actorsString,
                     String director,
                     boolean starMovie,
                     String trailerUrl,
                     String backgroundImgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coverPhotoUrl = coverPhotoUrl;
        this.genreString = genreString;
        this.actorsString = actorsString;
        this.director = director;
        this.releaseDate = releaseDate.toLocalDate();
        this.movieDuration = movieDuration;
        this.starMovie = starMovie;
        this.trailerUrl = trailerUrl;
        this.backgroundImgUrl = backgroundImgUrl;
    }

    private Long id;
    private String name;
    private String description;
    private String coverPhotoUrl;

    private String genres;
    private String genreString;

    private String actorsString;
    private String director;

    private Long viewCount;
    private LocalDate releaseDate;
    private String movieDuration;

    private boolean starMovie;
    private String trailerUrl;
    private String backgroundImgUrl;

    // String → List<Genre>
    public List<Genre> getGenres() {
        if (genreString == null || genreString.isBlank()) {
            return List.of();
        }
        return Arrays.stream(genreString.split(","))
                .map(Genre::valueOf)
                .collect(Collectors.toList());
    }

    // String → List<String>
    public List<String> getActors() {
        if (actorsString == null || actorsString.isBlank()) {
            return List.of();
        }
        return Arrays.asList(actorsString.split(","));
    }
}
