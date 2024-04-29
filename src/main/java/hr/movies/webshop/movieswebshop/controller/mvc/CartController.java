package hr.movies.webshop.movieswebshop.controller.mvc;

import hr.movies.webshop.movieswebshop.dto.MovieDTO;
import hr.movies.webshop.movieswebshop.model.MovieAgeRatingEnum;
import hr.movies.webshop.movieswebshop.model.MovieGenreEnum;
import hr.movies.webshop.movieswebshop.model.MovieSearchForm;
import hr.movies.webshop.movieswebshop.model.User;
import hr.movies.webshop.movieswebshop.publisher.CustomSpringEventPublisher;
import hr.movies.webshop.movieswebshop.repository.UserRepository;
import hr.movies.webshop.movieswebshop.service.MoviesService;
import hr.movies.webshop.movieswebshop.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/mvc/movieswebshop")
@AllArgsConstructor
public class CartController {

    private MoviesService moviesService;

    @GetMapping("/getCart.html")
    public String getCart(Model model) {
        if (!model.containsAttribute("movies"))
            model.addAttribute("movies", moviesService.getMovies());
        return "cart";
    }

    @GetMapping("/getPurchase.html")
    public String getPurchase(Model model) {
        return "purchase";
    }
}
