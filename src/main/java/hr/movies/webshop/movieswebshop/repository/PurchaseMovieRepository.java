package hr.movies.webshop.movieswebshop.repository;

import hr.movies.webshop.movieswebshop.model.PurchaseMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseMovieRepository extends JpaRepository<PurchaseMovie, Integer> {
}
