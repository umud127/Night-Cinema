package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.DTOUser;
import az.duo.Night.Cinema.dto.DTOUserIU;
import az.duo.Night.Cinema.entity.BaseEntity;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.StatusCode;
import az.duo.Night.Cinema.jwt.JWTService;
import az.duo.Night.Cinema.repository.RestUserRepo;
import az.duo.Night.Cinema.service.IRestUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestUserServiceIMPL implements IRestUserService {

    private final RestUserRepo restUserRepo;
    private final JWTService jwtService;

    public BaseEntity<DTOUser> getUser(String token) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            return BaseEntity.notOk(StatusCode.UNAUTHORIZED, "token is invalid", "/me");
        }

        if(!restUserRepo.existsById(id)) {
            return BaseEntity.notOk(StatusCode.NOT_FOUND, "User Not Found", "/me");
        }

        Optional<User> dbUser = restUserRepo.findById(id);

        if(dbUser.isPresent()) {
            DTOUser user = new DTOUser();
            BeanUtils.copyProperties(dbUser.get(), user);
            //eger menimsedilmeyen varsa manual menimsedecem
            return BaseEntity.ok(user);
        }

        return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, "Server Error: Unexpected Error", "/me");
    }

    @Override
    public BaseEntity<DTOUser> updateUser(String token, DTOUserIU user) {
        return null;
    }
}
