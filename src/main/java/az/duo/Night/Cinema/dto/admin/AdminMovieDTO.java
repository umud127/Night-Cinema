package az.duo.Night.Cinema.dto.admin;

import az.duo.Night.Cinema.enums.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminMovieDTO {

    private String name;
    private String description;

    private String movieDuration;
    private List<Genre> genre;

    private String director;
    private List<String> actors;

    private LocalDate releaseDate;
    private String trailerUrl;

    private boolean starMovie;

    @Schema(required = false)
    private MultipartFile coverPhoto;

    @Schema(required = false)
    private MultipartFile background;
}

