/*
package hr.movies.webshop.movieswebshop.repository;

import hr.movies.webshop.movieswebshop.model.*;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Primary
@Repository
public class JdbcMoviesRepositoryImplementation implements MoviesRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcMoviesInsert;

    public JdbcMoviesRepositoryImplementation(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcMoviesInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("Movie")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Movie> getMovies() {
        return jdbcTemplate.query("SELECT * FROM Movie",
                new BeanPropertyRowMapper<Movie>(Movie.class));
    }

    @Override
    public Movie getOneMovie(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Movie WHERE id = ?", new MovieRowMapper(), id);
    }

    @Override
    public Movie addNewMovie(Movie newMovie) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("genreId", newMovie.getGenre().getId());
        parameters.put("ageRatingId", newMovie.getAgeRating().getId());
        parameters.put("title", newMovie.getTitle());
        parameters.put("description", newMovie.getDescription());
        parameters.put("durationMinutes", newMovie.getDurationMinutes());
        parameters.put("releaseDate", newMovie.getReleaseDate());
        parameters.put("price", newMovie.getPrice());
        Integer id = simpleJdbcMoviesInsert.execute(parameters);
        newMovie.setId(id);
        return newMovie;
    }

    @Override
    public List<Movie> filterMovies(MovieSearchForm movieSearchForm) {
        StringBuilder sqlQuery = new StringBuilder("SELECT * FROM Movies where 1=1 ");
        List<Object> queryArgs = new ArrayList<>();

        if (movieSearchForm.getTitle() != null && !movieSearchForm.getTitle().isEmpty()) {
            sqlQuery.append("AND title LIKE CONCAT('%', ?, '%') ");
            queryArgs.add(movieSearchForm.getTitle());
        }
        if (movieSearchForm.getGenre() != null && !movieSearchForm.getGenre().isEmpty()) {
            sqlQuery.append("AND genreId = ? ");
            queryArgs.add(movieSearchForm.getGenre());
        }
        if (movieSearchForm.getAgeRating() != null && !movieSearchForm.getAgeRating().isEmpty()) {
            sqlQuery.append("AND ageRatingId = ? ");
            queryArgs.add(movieSearchForm.getAgeRating());
        }
        if (movieSearchForm.getReleaseDateFrom() != null) {
            sqlQuery.append("AND releaseDate >= ? ");
            queryArgs.add(movieSearchForm.getReleaseDateFrom());
        }
        if (movieSearchForm.getReleaseDateTo() != null) {
            sqlQuery.append("AND releaseDate <= ? ");
            queryArgs.add(movieSearchForm.getReleaseDateTo());
        }
        if (movieSearchForm.getPriceFrom() != null) {
            sqlQuery.append("AND price >= ? ");
            queryArgs.add(movieSearchForm.getPriceFrom());
        }
        if (movieSearchForm.getPriceTo() != null) {
            sqlQuery.append("AND price <= ? ");
            queryArgs.add(movieSearchForm.getPriceTo());
        }
        Object[] preparedStatementArgs = new Object[queryArgs.size()];
        for (int i = 0; i < preparedStatementArgs.length; i++) {
            preparedStatementArgs[i] = queryArgs.get(i);
        }
        return this.jdbcTemplate.query(sqlQuery.toString(), new MovieRowMapper(), preparedStatementArgs);
    }

    private static class MovieRowMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie newMovie = new Movie();
            newMovie.setId(rs.getInt("id"));
            newMovie.setTitle(rs.getString("title"));
            newMovie.setDescription(rs.getString("description"));
            newMovie.setDurationMinutes(rs.getInt("durationMinutes"));
            newMovie.setReleaseDate(rs.getDate("releaseDate").toLocalDate());
            newMovie.setPrice(rs.getBigDecimal("price"));
            MovieGenre genre = new MovieGenre();
            genre.setName(rs.getString("genre"));
            newMovie.setGenre(genre);
            MovieAgeRating ageRating = new MovieAgeRating();
            ageRating.setName(rs.getString("ageRating"));
            newMovie.setAgeRating(ageRating);
            return newMovie;
        }
    }
}
*/
