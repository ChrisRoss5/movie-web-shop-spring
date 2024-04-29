package hr.movies.webshop.movieswebshop.model;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PurchaseSpecifications {
    public static Specification<Purchase> hasUser(Integer userId) {
        return (root, query, cb) -> {
            if (userId == null) return null;
            return cb.equal(root.get("user").get("id"), userId);
        };
    }

    public static Specification<Purchase> occurredAfter(LocalDate purchaseDateFrom) {
        return (root, query, cb) -> {
            if (purchaseDateFrom == null) return null;
            return cb.greaterThanOrEqualTo(root.get("purchaseDate"), purchaseDateFrom);
        };
    }

    public static Specification<Purchase> occurredBefore(LocalDate purchaseDateTo) {
        return (root, query, cb) -> {
            if (purchaseDateTo == null) return null;
            return cb.lessThanOrEqualTo(root.get("purchaseDate"), purchaseDateTo);
        };
    }
}
