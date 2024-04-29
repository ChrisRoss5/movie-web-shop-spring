package hr.movies.webshop.movieswebshop.service;

import hr.movies.webshop.movieswebshop.dto.MovieDTO;
import hr.movies.webshop.movieswebshop.model.MovieSearchForm;

import java.util.List;
import java.util.Optional;

public interface MoviesService {
    List<MovieDTO> getMovies();
    Optional<MovieDTO> getMovie(Integer id);
    List<MovieDTO> filterMovies(MovieSearchForm movieSearchForm);
    MovieDTO createMovie(MovieDTO newMovie);
    void updateMovie(MovieDTO movieDTO);
    void deleteMovie(Integer id);
}
