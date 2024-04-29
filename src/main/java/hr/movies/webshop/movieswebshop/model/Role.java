package hr.movies.webshop.movieswebshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
