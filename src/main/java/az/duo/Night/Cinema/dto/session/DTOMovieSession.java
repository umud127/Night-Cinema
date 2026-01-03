package az.duo.Night.Cinema.dto.session;

import az.duo.Night.Cinema.dto.movie.DTOMovie2;
import az.duo.Night.Cinema.entity.Language;
import az.duo.Night.Cinema.entity.Room;
import az.duo.Night.Cinema.enums.Location;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DTOMovieSession {

    private DTOMovie2 movie;

    private Language language;

    private Location location;

    private Room room;

    private LocalDateTime startTime;

    private boolean dubbed;
    private boolean subbed;
}
