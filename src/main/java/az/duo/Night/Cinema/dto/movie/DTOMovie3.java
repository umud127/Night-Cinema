package az.duo.Night.Cinema.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOMovie3 {

    private String name;
    private String description;
    private String coverPhotoUrl;
    private String movieDuration;
    private String releaseDate;
    private String genres;
}
