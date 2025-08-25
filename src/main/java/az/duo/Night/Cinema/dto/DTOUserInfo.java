package az.duo.Night.Cinema.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DTOUserInfo {

    private String username;
    private String phoneNumber;
    private Date createdAt;

}
