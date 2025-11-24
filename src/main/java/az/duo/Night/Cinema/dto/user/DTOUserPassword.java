package az.duo.Night.Cinema.dto.user;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DTOUserPassword {

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String oldPassword;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String newPassword;

}
