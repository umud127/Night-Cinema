package az.duo.Night.Cinema.entity;

import az.duo.Night.Cinema.enums.Permission;
import az.duo.Night.Cinema.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Size(min = 2, message = "The name must be at least 2 characters long")
    @Column(unique = true, name = "username", nullable = false)
    @Pattern(
            regexp = "^[a-zA-Z0-9]+$",
            message = "Username should contain only letters and numbers"
    )
    private String username;

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format (e.g., example@mail.com)"
    )
    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Size(min = 8, max = 16)
    @Pattern(regexp = "^\\+?[1-9]\\d{7,14}$",
            message = "Phone number must be in E.164 format (e.g., +994501234567)")
    @Column(name = "phone_e164", length = 16)
    private String phoneE164;

    @Column(name = "got_movies", nullable = false, columnDefinition = "int default 0")
    private int gotMovies = 0;

    @Column(name = "got_tickets", nullable = false, columnDefinition = "int default 0")
    private int gotTickets = 0;

    @Column(name = "profile_photo_url" ,
            columnDefinition = "text default 'https://res.cloudinary.com/dvusim2rf/image/upload/v1760720515/hu8eqetp1qmn4krc2mdk.webp' ",
            nullable = false)
    private String profilePhotoUrl;

    //Enums(constant variables)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleName role = RoleName.USER;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "admin_permissions")
    private List<Permission> adminPermissions = null;

    @ManyToMany
    @JoinTable(
            name = "user_movie_session",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_session_id")
    )
    @JsonIgnore
    private List<MovieSession> movies;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    //Spring security methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
        return id.toString();
    }

    public String getRealUsername() {
        return username;
    }
}
