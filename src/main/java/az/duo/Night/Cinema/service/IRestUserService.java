package az.duo.Night.Cinema.service;

import az.duo.Night.Cinema.dto.user.DTOUserIU;
import az.duo.Night.Cinema.dto.user.DTOUserInfo;
import az.duo.Night.Cinema.dto.user.DTOUserMovie;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.enums.RoleName;

public interface IRestUserService {

    BaseEntity<RoleName> checkUserRole(String token);

    BaseEntity<DTOUserInfo> getUserInfo(String token);

    BaseEntity<DTOUserMovie> getUserMovie(String token);

    BaseEntity<String> updateUser(String token, DTOUserIU user);
}
