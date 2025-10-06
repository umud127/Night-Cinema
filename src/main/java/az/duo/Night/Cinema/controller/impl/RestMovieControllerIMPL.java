package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestMovieController;
import az.duo.Night.Cinema.dto.movie.DTOMovie;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.service.IRestMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class RestMovieControllerIMPL implements IRestMovieController {

    private final IRestMovieService restMovieService;

    @GetMapping("/star")
    @Override
    public BaseEntity<DTOMovie> getStarMovie() {
        return restMovieService.getStarMovie();
    }

    @GetMapping("/all")
    @Override
    public BaseEntity<List<DTOMovie>> getAllMovies() {
        return restMovieService.getAllMovies();
    }

    @GetMapping("/by_name")
    @Override
    public BaseEntity<List<DTOMovie>> getMoviesByName(String language) {
        return restMovieService.getMoviesByName(language);
    }
}
