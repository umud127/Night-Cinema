package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.dto.user.DTOUserIU;
import az.duo.Night.Cinema.dto.user.DTOUserInfo;
import az.duo.Night.Cinema.dto.user.DTOUserMovie;
import az.duo.Night.Cinema.dto.user.DTOUserSecurity;
import az.duo.Night.Cinema.entity.BaseEntity;

public interface IRestUserController {

    BaseEntity<DTOUserInfo> getUserInfo(String token);

    BaseEntity<DTOUserSecurity> getUserSecurity(String token);

    BaseEntity<DTOUserMovie> getUserMovie(String token);

    BaseEntity<String> updateUser(String token, DTOUserIU user);
}
