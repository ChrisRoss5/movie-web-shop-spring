package hr.movies.webshop.movieswebshop.repository;

import hr.movies.webshop.movieswebshop.model.MovieAgeRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieAgeRatingRepository extends JpaRepository<MovieAgeRating, Integer> {
}