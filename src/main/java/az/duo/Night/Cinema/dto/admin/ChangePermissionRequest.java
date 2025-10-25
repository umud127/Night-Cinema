package az.duo.Night.Cinema.dto.admin;

import az.duo.Night.Cinema.enums.Permission;
import lombok.Data;

import java.util.List;

@Data
public class ChangePermissionRequest {
    String username;
    List<Permission> permissions;
}
