package az.duo.Night.Cinema.repository;

import az.duo.Night.Cinema.dto.user.DTOUserInfo;
import az.duo.Night.Cinema.entity.MovieSession;
import az.duo.Night.Cinema.entity.User;
import az.duo.Night.Cinema.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestUserRepo extends JpaRepository<User, Long> {

    @Query("SELECT u.role FROM User u WHERE u.email = :email")
    RoleName getRoleByEmail(@Param("email") String email);

    @Query(value = """
       SELECT username AS realUsername,
              phone_e164 AS phoneE164,
              profile_photo_url AS profilePhotoUrl,
              email AS email,
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

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = az.duo.Night.Cinema.enums.RoleName.ADMIN")
    List<User> findAllAdmins();
}
