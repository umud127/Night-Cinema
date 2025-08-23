package az.duo.Night.Cinema.dto;

import az.duo.Night.Cinema.entity.Movie;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DTOUser {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    private Date createdAt;

    private int gotMovies = 0;
    private List<Movie> movies;
}
