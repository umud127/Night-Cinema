package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.dto.DTOUserIU;
import az.duo.Night.Cinema.dto.DTOUserInfo;
import az.duo.Night.Cinema.dto.DTOUserMovie;
import az.duo.Night.Cinema.dto.DTOUserSecurity;
import az.duo.Night.Cinema.entity.BaseEntity;

public interface IRestUserController {

    BaseEntity<DTOUserInfo> getUserInfo(String token);

    BaseEntity<DTOUserSecurity> getUserSecurity(String token);

    BaseEntity<DTOUserMovie> getUserMovie(String token);

    BaseEntity<DTOUserSecurity> updateUser(String token, DTOUserIU user);
}
