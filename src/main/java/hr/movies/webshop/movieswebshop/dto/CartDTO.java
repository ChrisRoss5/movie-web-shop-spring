package hr.movies.webshop.movieswebshop.dto;

import hr.movies.webshop.movieswebshop.model.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private PaymentMethodEnum paymentMethod;
    private List<PurchaseMovieDTO> purchaseMovieDTOs;
}
