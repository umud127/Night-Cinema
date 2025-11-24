package az.duo.Night.Cinema.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DTOUserIU {

    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    String username;

    @Size(min = 8, max = 16)
    @Pattern(regexp = "^\\+?[1-9]\\d{7,14}$",
            message = "Phone number must be in E.164 format (e.g., +994501234567)")
    String phoneNumber;

    @Email(message = "Invalid email format (e.g., )")
    String email;

}
