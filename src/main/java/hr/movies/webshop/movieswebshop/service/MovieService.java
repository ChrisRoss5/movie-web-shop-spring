package hr.movies.webshop.movieswebshop.service;

import hr.movies.webshop.movieswebshop.dto.MovieRequestDTO;
import hr.movies.webshop.movieswebshop.dto.MovieResponseDTO;
import hr.movies.webshop.movieswebshop.model.MovieSearchForm;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieResponseDTO> getMovies();
    Optional<MovieResponseDTO> getMovieResponseDTO(Integer id);
    Optional<MovieRequestDTO> getMovieRequestDTO(Integer id);
    List<MovieResponseDTO> filterMovies(MovieSearchForm movieSearchForm);
    void createMovie(MovieRequestDTO newMovieRequestDTO);
    void updateMovie(MovieRequestDTO movieRequestDTO);
    void deleteMovie(Integer id);
}
