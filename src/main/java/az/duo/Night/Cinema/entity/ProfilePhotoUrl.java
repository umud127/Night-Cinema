package az.duo.Night.Cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profile_photo_url")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfilePhotoUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_photo_url" , columnDefinition = "text default '' ", nullable = false)
    private String profilePhotoUrl;
}
