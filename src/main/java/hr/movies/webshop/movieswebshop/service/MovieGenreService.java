package hr.movies.webshop.movieswebshop.service;


import hr.movies.webshop.movieswebshop.model.MovieGenre;

import java.util.List;

public interface MovieGenreService {
    List<MovieGenre> getMovieGenres();
    void createMovieGenre(MovieGenre newMovieGenre);
    void updateMovieGenre(MovieGenre genre);
    void deleteMovieGenre(Integer id);
}
