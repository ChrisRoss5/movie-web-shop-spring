package hr.movies.webshop.movieswebshop.repository;

import hr.movies.webshop.movieswebshop.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer> {
}