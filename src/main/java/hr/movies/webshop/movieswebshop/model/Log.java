package hr.movies.webshop.movieswebshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "ipAddr", nullable = false, length = 25)
    private String ipAddr;

    @Column(name = "message", nullable = false, length = 255)
    private String message;

    @Column(name = "localDateTime", nullable = false)
    private LocalDateTime localDateTime;
}
