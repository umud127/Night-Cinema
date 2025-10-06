package az.duo.Night.Cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cinema_movie")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "cover_photo_url")
    private String coverPhotoUrl;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "movie_duration")
    private Long movieDuration;

    @Column(name = "star_movie", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean starMovie;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieSession> movieSessions = new ArrayList<>();
}
