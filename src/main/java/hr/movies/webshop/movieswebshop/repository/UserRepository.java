package hr.movies.webshop.movieswebshop.repository;


import hr.movies.webshop.movieswebshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
