package az.duo.Night.Cinema.jwt;

import lombok.Data;

@Data
public class AuthRequest {
    private String emailOrUsername;
    private String password;
}
