package hr.movies.webshop.movieswebshop.service;


import hr.movies.webshop.movieswebshop.dto.CartDTO;
import hr.movies.webshop.movieswebshop.model.HistorySearchForm;
import hr.movies.webshop.movieswebshop.model.Purchase;
import hr.movies.webshop.movieswebshop.model.User;
import jakarta.annotation.Nullable;

import java.util.List;

public interface PurchaseService {
    List<Purchase> getHistory(@Nullable User user);

    List<Purchase> filterHistory(HistorySearchForm historySearchForm);

    boolean purchase(CartDTO cartDTO, User user);
}
