package az.duo.Night.Cinema.repository;

import az.duo.Night.Cinema.dto.user.DTOUserInfo;
import az.duo.Night.Cinema.entity.MovieSession;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.Permission;
import az.duo.Night.Cinema.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestUserRepo extends JpaRepository<User, Long> {

    @Query("SELECT u.role FROM User u WHERE u.id = :id")
    RoleName getRoleById(@Param("id") Long id);

    @Query(value = """
       SELECT username,
              phone_e164 AS phoneNumber,
              profile_photo_url AS profilePhotoUrl,
              email,
              created_at AS createdAt
       FROM app_user
       WHERE id = :id
       """, nativeQuery = true)
    Optional<DTOUserInfo> getUserInfoById(@Param("id") Long id);


    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("SELECT u.movies FROM User u WHERE u.id = :userId")
    Optional<List<MovieSession>> findMovieSessionsByUserId(@Param("userId") Long userId);

    Optional<User> findAdminByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.role = az.duo.Night.Cinema.enums.RoleName.USER")
    Optional<User> findUserByUsername(@Param("username") String username);


    //Find for Role
    @Query("SELECT u FROM User u WHERE u.role = az.duo.Night.Cinema.enums.RoleName.ADMIN")
    List<User> findAllAdmins();

    @Query("SELECT u FROM User u WHERE u.role = az.duo.Night.Cinema.enums.RoleName.USER")
    List<User> findAllUsers();

    @Query("SELECT u.adminPermissions FROM User u WHERE u.username = :username")
    List<Permission> findAdminPermissions(@Param("username") String username);

    Long id(Long id);
}
