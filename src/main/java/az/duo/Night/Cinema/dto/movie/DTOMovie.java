package az.duo.Night.Cinema.dto.movie;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DTOMovie {

    private String name;

    private String description;

    private String coverPhotoUrl;

    private LocalDateTime date;
}
