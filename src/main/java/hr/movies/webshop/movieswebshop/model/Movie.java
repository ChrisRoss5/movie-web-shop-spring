package hr.movies.webshop.movieswebshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Movie")
public class Movie implements Serializable {

    @Serial
    private static final long serialVersionUID = -3387516993124229948L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @Column(name = "thumbnailUrl", length = 2048, nullable = true)
    private String thumbnailUrl;

    @Column(name = "durationMinutes", nullable = false)
    private Integer durationMinutes;

    @Column(name = "releaseDate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Column(name = "price", precision = 8, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genreId", referencedColumnName = "id", nullable = false)
    private MovieGenre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ageRatingId", referencedColumnName = "id", nullable = false)
    private MovieAgeRating ageRating;
}
