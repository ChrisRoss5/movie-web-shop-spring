package hr.movies.webshop.movieswebshop.repository;

import hr.movies.webshop.movieswebshop.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Integer> {
}