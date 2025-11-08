package az.duo.Night.Cinema.entity;

import az.duo.Night.Cinema.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Column(name = "background_img_url")
    private String backgroundImgUrl;

    @Column(name = "view_count",columnDefinition = "Long default 0")
    private Long viewCount;

    @Column(name = "movie_duration")
    private String movieDuration;

    @Column(name = "star_movie", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean starMovie;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private List<Genre> genres = new ArrayList<>();

    @Column(name = "director")
    private String director;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "actor")
    private List<String> actors = new ArrayList<>();

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieSession> movieSessions = new ArrayList<>();
}
