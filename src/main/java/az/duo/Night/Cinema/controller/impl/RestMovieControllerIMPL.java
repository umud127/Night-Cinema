package az.duo.Night.Cinema.controller.impl;

import az.duo.Night.Cinema.controller.IRestMovieController;
import az.duo.Night.Cinema.dto.movie.DTOStarMovie;
import az.duo.Night.Cinema.dto.movie.DTOMovieAll;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.service.IRestMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class RestMovieControllerIMPL implements IRestMovieController {

    private final IRestMovieService restMovieService;

    @GetMapping("/star")
    @Override
    public BaseEntity<List<DTOStarMovie>> getStarMovie() {
        return restMovieService.getStarMovie();
    }

    @GetMapping("/all")
    @Override
    public BaseEntity<List<DTOMovieAll>> getAllMovies() {
        return restMovieService.getAllMovies();
    }

    @GetMapping("/by_name/{movieName}")
    @Override
    public BaseEntity<List<DTOMovieAll>> getMoviesByName(@PathVariable(name = "movieName") String movieName) {
        return restMovieService.getMoviesByName(movieName);
    }
}
