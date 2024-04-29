package hr.movies.webshop.movieswebshop.service;

import hr.movies.webshop.movieswebshop.dto.CartDTO;
import hr.movies.webshop.movieswebshop.model.*;
import hr.movies.webshop.movieswebshop.repository.MovieRepository;
import hr.movies.webshop.movieswebshop.repository.PurchaseRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static hr.movies.webshop.movieswebshop.model.PurchaseSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private MovieRepository jpaMoviesRepository;
    private PurchaseRepository jpaPurchaseRepository;

    @Override
    public List<Purchase> getHistory(@Nullable User user) {
        if (user == null)
            return jpaPurchaseRepository.findAll();
        return jpaPurchaseRepository.findByUser(user);
    }

    @Override
    public List<Purchase> filterHistory(HistorySearchForm historySearchForm) {
        Specification<Purchase> spec = where(hasUser(historySearchForm.getUserId()))
                .and(occurredAfter(historySearchForm.getPurchaseDateFrom()))
                .and(occurredBefore(historySearchForm.getPurchaseDateTo()));
        return jpaPurchaseRepository.findAll(spec);
    }

    @Override
    public boolean purchase(CartDTO cartDTO, User user) {
        List<PurchaseMovie> purchaseMovies = new ArrayList<>();
        Purchase purchase = new Purchase(null,
                cartDTO.getPaymentMethod().name(), LocalDate.now(), BigDecimal.ZERO, user, purchaseMovies);
        BigDecimal totalAmount = cartDTO.getPurchaseMovieDTOs().stream()
                .map(purchaseMovieDTO -> {
                    Movie movie = jpaMoviesRepository.findById(purchaseMovieDTO.getMovieId()).orElse(null);
                    if (movie == null)
                        return BigDecimal.ZERO;
                    purchaseMovies.add(new PurchaseMovie(null, purchase, movie, purchaseMovieDTO.getQuantity()));
                    return movie.getPrice().multiply(BigDecimal.valueOf(purchaseMovieDTO.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        purchase.setTotalAmount(totalAmount);
        jpaPurchaseRepository.save(purchase);
        return true;
    }
}
