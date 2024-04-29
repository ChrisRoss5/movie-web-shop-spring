package hr.movies.webshop.movieswebshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseMovieDTO {
    private Integer movieId;
    private Integer quantity;
}
