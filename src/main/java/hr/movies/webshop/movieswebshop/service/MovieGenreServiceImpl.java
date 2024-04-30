package hr.movies.webshop.movieswebshop.service;

import hr.movies.webshop.movieswebshop.model.MovieGenre;
import hr.movies.webshop.movieswebshop.repository.MovieGenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieGenreServiceImpl implements MovieGenreService {

    private MovieGenreRepository jpaMovieGenresRepository;

    @Override
    public List<MovieGenre> getMovieGenres() {
        return jpaMovieGenresRepository.findAll().reversed();
    }

    @Override
    public void createMovieGenre(MovieGenre newMovieGenre) {
        jpaMovieGenresRepository.save(newMovieGenre);
    }

    @Override
    public void updateMovieGenre(MovieGenre genre) {
        jpaMovieGenresRepository.save(genre);
    }

    @Override
    public void deleteMovieGenre(Integer id) {
        jpaMovieGenresRepository.deleteById(id);
    }
}
