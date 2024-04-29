package hr.movies.webshop.movieswebshop.model;

import lombok.Getter;

@Getter
public enum MovieAgeRatingEnum {
    G("General Audience"),
    PG("Parental Guidance Suggested"),
    PG_13("Parents Strongly Cautioned"),
    R("Restricted"),
    NC_17("No One 17 and Under Admitted");

    private final String description;

    MovieAgeRatingEnum(String description) {
        this.description = description;
    }
}
