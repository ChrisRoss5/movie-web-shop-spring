package hr.movies.webshop.movieswebshop.repository;

import hr.movies.webshop.movieswebshop.model.Purchase;
import hr.movies.webshop.movieswebshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>, JpaSpecificationExecutor<Purchase> {
    @Query("SELECT p FROM Purchase p WHERE p.user = :user")
    List<Purchase> findByUser(User user);
}

