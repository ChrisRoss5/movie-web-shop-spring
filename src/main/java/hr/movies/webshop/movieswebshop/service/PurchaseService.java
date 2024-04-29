package hr.movies.webshop.movieswebshop.service;


import hr.movies.webshop.movieswebshop.dto.CartDTO;
import hr.movies.webshop.movieswebshop.model.Purchase;
import hr.movies.webshop.movieswebshop.model.User;

import java.util.List;

public interface PurchaseService {
    List<Purchase> getPurchaseHistory(User user);
    boolean purchase(CartDTO cartDTO, User user);
}
