package az.duo.Night.Cinema.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOMovie {

    private String name;

    private String description;

    private String backgroundImgUrl;

    private Long movieDuration;
}
