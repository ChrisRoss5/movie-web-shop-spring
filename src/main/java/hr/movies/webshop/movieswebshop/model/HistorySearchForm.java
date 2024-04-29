package hr.movies.webshop.movieswebshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorySearchForm {
    private Integer userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDateTo;
}
