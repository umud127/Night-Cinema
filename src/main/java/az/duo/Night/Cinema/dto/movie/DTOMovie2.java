package az.duo.Night.Cinema.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOMovie2 {

    private String name;

    private String description;

    private String coverPhotoUrl;

    private Long movieDuration;

    private LocalDateTime startTime;
}
