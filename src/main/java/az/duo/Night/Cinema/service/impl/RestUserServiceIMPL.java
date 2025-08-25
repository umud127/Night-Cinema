package az.duo.Night.Cinema.service.impl;

import az.duo.Night.Cinema.dto.DTOUserIU;
import az.duo.Night.Cinema.dto.DTOUserInfo;
import az.duo.Night.Cinema.dto.DTOUserMovie;
import az.duo.Night.Cinema.dto.DTOUserSecurity;
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

    public BaseEntity<DTOUserInfo> getUserInfo(String token) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            return BaseEntity.notOk(StatusCode.UNAUTHORIZED, "token is invalid", "/me");
        }

        if(!restUserRepo.existsById(id)) {
            return BaseEntity.notOk(StatusCode.NOT_FOUND, "User Not Found", "/me");
        }

        Optional<User> dbUser = restUserRepo.findById(id);

        if(dbUser.isPresent()) {
            DTOUserInfo user = new DTOUserInfo();
            BeanUtils.copyProperties(dbUser.get(), user);
            //eger menimsedilmeyen varsa manual menimsedecem
            return BaseEntity.ok(user);
        }

        return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, "Server Error: Unexpected Error", "/me");
    }

    public BaseEntity<DTOUserSecurity> getUserSecurity(String token) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            return BaseEntity.notOk(StatusCode.UNAUTHORIZED, "token is invalid", "/me");
        }

        if(!restUserRepo.existsById(id)) {
            return BaseEntity.notOk(StatusCode.NOT_FOUND, "User Not Found", "/me");
        }

        Optional<User> dbUser = restUserRepo.findById(id);

        if(dbUser.isPresent()) {
            DTOUserSecurity user = new DTOUserSecurity();
            BeanUtils.copyProperties(dbUser.get(), user);
            //eger menimsedilmeyen varsa manual menimsedecem
            return BaseEntity.ok(user);
        }

        return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, "Server Error: Unexpected Error", "/me");
    }

    public BaseEntity<DTOUserMovie> getUserMovie(String token) {
        Long id = jwtService.extractIdFromToken(token);

        if(id == null) {
            return BaseEntity.notOk(StatusCode.UNAUTHORIZED, "token is invalid", "/me");
        }

        if(!restUserRepo.existsById(id)) {
            return BaseEntity.notOk(StatusCode.NOT_FOUND, "User Not Found", "/me");
        }

        Optional<User> dbUser = restUserRepo.findById(id);

        if(dbUser.isPresent()) {
            DTOUserMovie user = new DTOUserMovie();
            BeanUtils.copyProperties(dbUser.get(), user);
            //eger menimsedilmeyen varsa manual menimsedecem
            return BaseEntity.ok(user);
        }

        return BaseEntity.notOk(StatusCode.INTERNAL_SERVER_ERROR, "Server Error: Unexpected Error", "/me");
    }

    @Override
    public BaseEntity<DTOUserSecurity> updateUser(String token, DTOUserIU user) {
        return null;
    }
}
