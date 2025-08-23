package az.duo.Night.Cinema.controller;

import az.duo.Night.Cinema.dto.DTOUser;
import az.duo.Night.Cinema.dto.DTOUserIU;
import az.duo.Night.Cinema.entity.BaseEntity;

public interface IRestUserController {

    BaseEntity<DTOUser> getUser(String token);

    BaseEntity<DTOUser> updateUser(String token, DTOUserIU user);
}
