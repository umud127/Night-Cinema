package az.duo.Night.Cinema.service;

import az.duo.Night.Cinema.dto.DTOUser;
import az.duo.Night.Cinema.dto.DTOUserIU;
import az.duo.Night.Cinema.entity.BaseEntity;

public interface IRestUserService {

    BaseEntity<DTOUser> getUser(String token);

    BaseEntity<DTOUser> updateUser(String token, DTOUserIU user);
}
