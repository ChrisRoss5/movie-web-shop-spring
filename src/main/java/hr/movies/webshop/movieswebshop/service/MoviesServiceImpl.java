package hr.movies.webshop.movieswebshop.service;

import hr.movies.webshop.movieswebshop.dto.MovieDTO;
import hr.movies.webshop.movieswebshop.model.*;
import hr.movies.webshop.movieswebshop.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static hr.movies.webshop.movieswebshop.model.MovieSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@AllArgsConstructor
public class MoviesServiceImpl implements MoviesService {

    // private MoviesRepository jdbcMoviesRepository;
    private MovieRepository jpaMoviesRepository;

    @Override
    public List<MovieDTO> getMovies() {
        return jpaMoviesRepository.findAll().stream()
                .map(this::convertMovieToMovieDTO)
                .toList();
    }

    @Override
    public Optional<MovieDTO> getMovie(Integer id) {
        Optional<Movie> movieOptional = jpaMoviesRepository.findById(id);
        return movieOptional.map(this::convertMovieToMovieDTO);
    }

    @Override
    public MovieDTO createMovie(MovieDTO newMovie) {
        return convertMovieToMovieDTO(jpaMoviesRepository.save(convertMovieDtoToMovie(newMovie)));
    }

    @Override
    public void updateMovie(MovieDTO movieDTO) {
        jpaMoviesRepository.save(convertMovieDtoToMovie(movieDTO));
    }

    @Override
    public List<MovieDTO> filterMovies(MovieSearchForm movieSearchForm) {
        Specification<Movie> spec = where(includesTitle(movieSearchForm.getTitle()))
                .and(includesDescription(movieSearchForm.getDescription()))
                .and(hasGenre(movieSearchForm.getGenre()))
                .and(hasAgeRating(movieSearchForm.getAgeRating()))
                .and(releasedAfter(movieSearchForm.getReleaseDateFrom()))
                .and(releasedBefore(movieSearchForm.getReleaseDateTo()))
                .and(priceBetween(movieSearchForm.getPriceFrom(), movieSearchForm.getPriceTo()));
        return jpaMoviesRepository.findAll(spec)
                .stream()
                .map(this::convertMovieToMovieDTO)
                .toList();
    }

    @Override
    public void deleteMovie(Integer id) {
        jpaMoviesRepository.deleteById(id);
    }

    private Movie convertMovieDtoToMovie(MovieDTO movieDTO) {
        MovieGenre movieGenre = new MovieGenre();
        movieGenre.setName(movieDTO.getGenre().name());
        MovieAgeRating movieAgeRating = new MovieAgeRating();
        movieAgeRating.setName(movieDTO.getAgeRating().name());

        return new Movie(
                null,
                movieDTO.getTitle(),
                movieDTO.getDescription(),
                movieDTO.getThumbnailUrl(),
                movieDTO.getDurationMinutes(),
                movieDTO.getReleaseDate(),
                movieDTO.getPrice(),
                movieGenre,
                movieAgeRating
        );
    }

    private MovieDTO convertMovieToMovieDTO(Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getThumbnailUrl(),
                movie.getDurationMinutes(),
                movie.getReleaseDate(),
                movie.getPrice(),
                MovieGenreEnum.valueOf(movie.getGenre().getName()),
                MovieAgeRatingEnum.valueOf(movie.getAgeRating().getName())
        );
    }
}
