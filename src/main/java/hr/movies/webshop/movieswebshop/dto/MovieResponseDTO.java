package hr.movies.webshop.movieswebshop.dto;

import hr.movies.webshop.movieswebshop.model.MovieAgeRating;
import hr.movies.webshop.movieswebshop.model.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {
    private Integer id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private Integer durationMinutes;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private BigDecimal price;
    private MovieGenre genre;
    private MovieAgeRating ageRating;
}
