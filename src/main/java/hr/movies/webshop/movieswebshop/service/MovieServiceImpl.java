package hr.movies.webshop.movieswebshop.service;

import hr.movies.webshop.movieswebshop.dto.MovieRequestDTO;
import hr.movies.webshop.movieswebshop.dto.MovieResponseDTO;
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
public class MovieServiceImpl implements MovieService {

    private MovieRepository jpaMoviesRepository;

    @Override
    public List<MovieResponseDTO> getMovies() {
        return jpaMoviesRepository.findAll().stream()
                .map(this::convertMovieToMovieResponseDTO)
                .toList().reversed();
    }

    @Override
    public Optional<MovieResponseDTO> getMovieResponseDTO(Integer id) {
        Optional<Movie> movieOptional = jpaMoviesRepository.findById(id);
        return movieOptional.map(this::convertMovieToMovieResponseDTO);
    }

    @Override
    public Optional<MovieRequestDTO> getMovieRequestDTO(Integer id) {
        Optional<Movie> movieOptional = jpaMoviesRepository.findById(id);
        return movieOptional.map(this::convertMovieToMovieRequestDTO);
    }

    @Override
    public List<MovieResponseDTO> filterMovies(MovieSearchForm movieSearchForm) {
        Specification<Movie> spec = where(includesTitle(movieSearchForm.getTitle()))
                .and(includesDescription(movieSearchForm.getDescription()))
                .and(hasGenre(movieSearchForm.getGenreId()))
                .and(hasAgeRating(movieSearchForm.getAgeRatingId()))
                .and(releasedAfter(movieSearchForm.getReleaseDateFrom()))
                .and(releasedBefore(movieSearchForm.getReleaseDateTo()))
                .and(priceBetween(movieSearchForm.getPriceFrom(), movieSearchForm.getPriceTo()))
                .and(durationBetween(movieSearchForm.getDurationMinutesFrom(), movieSearchForm.getDurationMinutesTo()));
        return jpaMoviesRepository.findAll(spec)
                .stream()
                .map(this::convertMovieToMovieResponseDTO)
                .toList().reversed();
    }

    @Override
    public void createMovie(MovieRequestDTO newMovieRequestDTO) {
        jpaMoviesRepository.save(convertMovieRequestDtoToMovie(newMovieRequestDTO));
    }

    @Override
    public void updateMovie(MovieRequestDTO movieRequestDTO) {
        jpaMoviesRepository.save(convertMovieRequestDtoToMovie(movieRequestDTO));
    }

    @Override
    public void deleteMovie(Integer id) {
        jpaMoviesRepository.deleteById(id);
    }

    private Movie convertMovieRequestDtoToMovie(MovieRequestDTO movieRequestDTO) {
        MovieGenre movieGenre = new MovieGenre();
        movieGenre.setId(movieRequestDTO.getGenreId());
        MovieAgeRating movieAgeRating = new MovieAgeRating();
        movieAgeRating.setId(movieRequestDTO.getAgeRatingId());
        return new Movie(
                movieRequestDTO.getId(),
                movieRequestDTO.getTitle(),
                movieRequestDTO.getDescription(),
                movieRequestDTO.getThumbnailUrl(),
                movieRequestDTO.getDurationMinutes(),
                movieRequestDTO.getReleaseDate(),
                movieRequestDTO.getPrice(),
                movieGenre,
                movieAgeRating
        );
    }

    private MovieResponseDTO convertMovieToMovieResponseDTO(Movie movie) {
        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getThumbnailUrl(),
                movie.getDurationMinutes(),
                movie.getReleaseDate(),
                movie.getPrice(),
                movie.getGenre(),
                movie.getAgeRating()
        );
    }

    private MovieRequestDTO convertMovieToMovieRequestDTO(Movie movie) {
        return new MovieRequestDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getThumbnailUrl(),
                movie.getDurationMinutes(),
                movie.getReleaseDate(),
                movie.getPrice(),
                movie.getGenre().getId(),
                movie.getAgeRating().getId()
        );
    }
}
