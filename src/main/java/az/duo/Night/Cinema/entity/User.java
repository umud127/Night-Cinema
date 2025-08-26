package az.duo.Night.Cinema.entity;

import az.duo.Night.Cinema.enums.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "username")
    private String username;

    @Column(unique = true, name = "email")
    private String email;

//    @Size(min = 8, max = 25)
    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "got_movies")
    private int gotMovies = 0;

    //Enums(constant variables)
    @Enumerated(EnumType.STRING)
    private RoleName role = RoleName.USER;

    @ManyToMany
    @JoinTable(
            name = "user_movie_session",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_session_id")
    )
    private List<MovieSession> movies;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    //Spring security methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of((GrantedAuthority) () -> role.name());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //My custom methods
    @Override
    public String getUsername() {
        return email;
    }

    public String getRealUsername() {
        return username;
    }
}
