package az.duo.Night.Cinema.dto.movie;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DTOMovie {

    private String name;

    private String description;

    private String coverPhotoUrl;

    private LocalDateTime date;
}
