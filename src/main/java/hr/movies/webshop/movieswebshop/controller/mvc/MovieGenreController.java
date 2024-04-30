package hr.movies.webshop.movieswebshop.controller.mvc;

import hr.movies.webshop.movieswebshop.model.MovieGenre;
import hr.movies.webshop.movieswebshop.service.MovieGenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mvc/movieswebshop")
@AllArgsConstructor
public class MovieGenreController {

    private MovieGenreService movieGenreService;

    @GetMapping("/getMovieGenres.html")
    public String getMovieGenres(Model model) {
        model.addAttribute("movieGenres", movieGenreService.getMovieGenres());
        model.addAttribute("movieGenre", new MovieGenre());
        return "movieGenres";
    }

    @PostMapping("/createMovieGenre.html")
    public String createMovieGenre(Model model, MovieGenre movieGenre, RedirectAttributes redirectAttrs) {
        try {
            movieGenreService.createMovieGenre(movieGenre);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:getMovieGenres.html";
    }

    @PostMapping("/updateMovieGenre.html")
    public String updateMovieGenre(Model model, MovieGenre movieGenre, RedirectAttributes redirectAttrs) {
        try {
            movieGenreService.updateMovieGenre(movieGenre);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:getMovieGenres.html";
    }

    @PostMapping("/deleteMovieGenre.html")
    public String deleteMovieGenre(Model model, MovieGenre movieGenre) {
        movieGenreService.deleteMovieGenre(movieGenre.getId());
        return "redirect:getMovieGenres.html";
    }
}
