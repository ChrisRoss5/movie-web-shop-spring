package hr.movies.webshop.movieswebshop.controller.mvc;

import hr.movies.webshop.movieswebshop.dto.MovieRequestDTO;
import hr.movies.webshop.movieswebshop.model.MovieSearchForm;
import hr.movies.webshop.movieswebshop.publisher.CustomSpringEventPublisher;
import hr.movies.webshop.movieswebshop.repository.MovieAgeRatingRepository;
import hr.movies.webshop.movieswebshop.service.MovieGenreService;
import hr.movies.webshop.movieswebshop.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mvc/movieswebshop")
@AllArgsConstructor
@SessionAttributes({"movieSearchForm", "movieGenres", "movieAgeRatings"})
public class MovieController {

    private MovieAgeRatingRepository movieAgeRatingRepository;
    private MovieService movieService;
    private MovieGenreService movieGenreService;
    private CustomSpringEventPublisher publisher;

    @GetMapping("/getMovies.html")
    public String getMovies(Model model) {
        publisher.publishCustomEvent("MovieController :: Search movies screen displayed!");
        if (!model.containsAttribute("movieSearchForm"))
            model.addAttribute("movieSearchForm", new MovieSearchForm());
        model.addAttribute("movies",
                movieService.filterMovies((MovieSearchForm) model.getAttribute("movieSearchForm")));
        addDropdownsToModel(model);
        return "movies";
    }

    @PostMapping("/searchMovies.html")
    public String searchMovies(Model model, MovieSearchForm movieSearchForm,
                               @RequestParam(name = "clearFilters", required = false) String clearFilters) {
        if (clearFilters != null) movieSearchForm = new MovieSearchForm();
        model.addAttribute("movieSearchForm", movieSearchForm);
        return "redirect:getMovies.html";
    }

    @GetMapping("/getMovieForm.html")
    public String getMovieForm(Model model, @RequestParam(required = false) Integer id) {
        if (id != null) {
            MovieRequestDTO movieRequestDTO = movieService.getMovieRequestDTO(id).orElse(null);
            if (movieRequestDTO == null) {
                model.addAttribute("errorMessage", "Movie not found!");
                return "/error/404";
            }
            model.addAttribute("movie", movieRequestDTO);
        } else if (!model.containsAttribute("movie"))
            model.addAttribute("movie", new MovieRequestDTO());
        addDropdownsToModel(model);
        return "movieForm";
    }

    @PostMapping("/createMovie.html")
    public String createMovie(Model model, MovieRequestDTO movieRequestDTO, RedirectAttributes redirectAttrs) {
        try {
            movieService.createMovie(movieRequestDTO);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttrs.addFlashAttribute("movie", movieRequestDTO);
            return "redirect:getMovieForm.html";
        }
        return "redirect:getMovies.html";
    }

    @PostMapping("/updateMovie.html")
    public String updateMovie(Model model, MovieRequestDTO movieRequestDTO, RedirectAttributes redirectAttrs) {
        try {
            movieService.updateMovie(movieRequestDTO);
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:getMovieForm.html?id=" + movieRequestDTO.getId();
        }
        return "redirect:getMovies.html";
    }

    @PostMapping("/deleteMovie.html")
    public String deleteMovie(Model model, MovieRequestDTO movieRequestDTO) {
        movieService.deleteMovie(movieRequestDTO.getId());
        return "redirect:getMovies.html";
    }

    private void addDropdownsToModel(Model model) {
        if (!model.containsAttribute("movieGenres"))
            model.addAttribute("movieGenres", movieGenreService.getMovieGenres());
        if (!model.containsAttribute("movieAgeRatings"))
            model.addAttribute("movieAgeRatings", movieAgeRatingRepository.findAll());
    }
}
