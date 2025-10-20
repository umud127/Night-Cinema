package az.duo.Night.Cinema.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class DTOUserInfo {

    private String username;
    private String phoneNumber;
    private Date createdAt;
    private String profilePhotoUrl;
}
