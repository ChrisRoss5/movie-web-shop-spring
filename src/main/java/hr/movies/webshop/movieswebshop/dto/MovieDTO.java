package hr.movies.webshop.movieswebshop.dto;

import hr.movies.webshop.movieswebshop.model.MovieAgeRatingEnum;
import hr.movies.webshop.movieswebshop.model.MovieGenreEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private Integer id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private Integer durationMinutes;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private BigDecimal price;
    private MovieGenreEnum genre;
    private MovieAgeRatingEnum ageRating;
}
