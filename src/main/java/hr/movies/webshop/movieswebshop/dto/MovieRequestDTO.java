package hr.movies.webshop.movieswebshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {
    private Integer id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private Integer durationMinutes;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private BigDecimal price;
    private Integer genreId;
    private Integer ageRatingId;
}
