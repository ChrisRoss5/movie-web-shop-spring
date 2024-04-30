package hr.movies.webshop.movieswebshop.model;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovieSpecifications {
    public static Specification<Movie> includesTitle(String title) {
        return (root, query, cb) -> {
            if (title == null || title.isEmpty()) return null;
            return cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
        };
    }

    public static Specification<Movie> includesDescription(String description) {
        return (root, query, cb) -> {
            if (description == null || description.isEmpty()) return null;
            return cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
        };
    }

    public static Specification<Movie> hasGenre(Integer genreId) {
        return (root, query, cb) -> {
            if (genreId == null) return null;
            return cb.equal(root.get("genre").get("id"), genreId);
        };
    }

    public static Specification<Movie> hasAgeRating(Integer ageRatingId) {
        return (root, query, cb) -> {
            if (ageRatingId == null) return null;
            return cb.equal(root.get("ageRating").get("id"), ageRatingId);
        };
    }

    public static Specification<Movie> releasedAfter(LocalDate releaseDateFrom) {
        return (root, query, cb) -> {
            if (releaseDateFrom == null) return null;
            return cb.greaterThanOrEqualTo(root.get("releaseDate"), releaseDateFrom);
        };
    }

    public static Specification<Movie> releasedBefore(LocalDate releaseDateTo) {
        return (root, query, cb) -> {
            if (releaseDateTo == null) return null;
            return cb.lessThanOrEqualTo(root.get("releaseDate"), releaseDateTo);
        };
    }

    public static Specification<Movie> priceBetween(BigDecimal priceFrom, BigDecimal priceTo) {
        return (root, query, cb) -> {
            if (priceFrom == null && priceTo == null) return null;
            if (priceFrom != null && priceTo == null) return cb.greaterThanOrEqualTo(root.get("price"), priceFrom);
            if (priceFrom == null) return cb.lessThanOrEqualTo(root.get("price"), priceTo);
            return cb.between(root.get("price"), priceFrom, priceTo);
        };
    }

    public static Specification<Movie> durationBetween(Integer durationMinutesFrom, Integer durationMinutesTo) {
        return (root, query, cb) -> {
            if (durationMinutesFrom == null && durationMinutesTo == null) return null;
            if (durationMinutesFrom != null && durationMinutesTo == null)
                return cb.greaterThanOrEqualTo(root.get("durationMinutes"), durationMinutesFrom);
            if (durationMinutesFrom == null)
                return cb.lessThanOrEqualTo(root.get("durationMinutes"), durationMinutesTo);
            return cb.between(root.get("durationMinutes"), durationMinutesFrom, durationMinutesTo);
        };
    }
}
