package az.duo.Night.Cinema.jwt;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
