package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.dto.user.*;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.enums.RoleName;
import org.springframework.web.multipart.MultipartFile;

public interface IRestUserController {

    BaseEntity<RoleName> checkUserRole(String token);

    BaseEntity<DTOUserInfo> getUserInfo(String token);

    BaseEntity<DTOUserMovie> getUserMovie(String token);

    BaseEntity<String> updateUserInfos(String token, DTOUserIU user);

    BaseEntity<String> updateUserPassword(String token, DTOUserPassword password);

    BaseEntity<String> updateUserProfilePhoto(String token, MultipartFile photo);
}
