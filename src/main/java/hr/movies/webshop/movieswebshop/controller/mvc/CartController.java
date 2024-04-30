package hr.movies.webshop.movieswebshop.controller.mvc;

import hr.movies.webshop.movieswebshop.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mvc/movieswebshop")
@AllArgsConstructor

public class CartController {

    private MovieService movieService;

    @GetMapping("/getCart.html")
    public String getCart(Model model) {
        model.addAttribute("movies", movieService.getMovies());
        return "cart";
    }

    @GetMapping("/getPurchase.html")
    public String getPurchase(Model model) {
        return "purchase";
    }
}
