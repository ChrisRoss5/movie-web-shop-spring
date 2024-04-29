package hr.movies.webshop.movieswebshop.controller.mvc;

import hr.movies.webshop.movieswebshop.dto.MovieDTO;
import hr.movies.webshop.movieswebshop.model.*;
import hr.movies.webshop.movieswebshop.publisher.CustomSpringEventPublisher;
import hr.movies.webshop.movieswebshop.service.MoviesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/mvc/movieswebshop")
@AllArgsConstructor
@SessionAttributes({"movieSearchForm", "movieGenres", "movieAgeRatings", "movies"})
public class MoviesController {

    private MoviesService moviesService;
    private CustomSpringEventPublisher publisher;

    @GetMapping("/getMovies.html")
    public String getMovies(Model model) {
        publisher.publishCustomEvent("MoviesController :: Search movies screen displayed!");
        if (!model.containsAttribute("movieSearchForm"))
            model.addAttribute("movieSearchForm", new MovieSearchForm());
        if (!model.containsAttribute("engineTypes"))
            model.addAttribute("movieGenres", MovieGenreEnum.values());
        if (!model.containsAttribute("colors"))
            model.addAttribute("movieAgeRatings", MovieAgeRatingEnum.values());
        if (!model.containsAttribute("movies"))
            model.addAttribute("movies", moviesService.getMovies());
        return "movies";
    }

    @PostMapping("/searchMovies.html")
    public String searchMovies(MovieSearchForm movieSearchForm,
                               @RequestParam(name = "clearFilters", required = false)
                               String clearFilters, SessionStatus sessionStatus, Model model) {
        if (clearFilters != null) {
            movieSearchForm = new MovieSearchForm();
            sessionStatus.setComplete();
        }
        List<MovieDTO> movies = moviesService.filterMovies(movieSearchForm);
        model.addAttribute("movies", movies);
        return "redirect:/mvc/movieswebshop/getMovies.html";
    }

    @GetMapping("/getMovieForm.html")
    public String getMovieForm(Model model, @RequestParam(required = false) Integer id) {
        if (id != null) {
            MovieDTO movieDTO = moviesService.getMovie(id).orElse(null);
            if (movieDTO == null) return "404";
            model.addAttribute("movie", movieDTO);
        } else
            model.addAttribute("movie", new MovieDTO());
        return "movieForm";
    }

    @PostMapping("/createMovieForm.html")
    public String createMovieForm(Model model, MovieDTO movieDTO) {
        moviesService.createMovie(movieDTO);
        return "movieForm";
    }

    @PostMapping("/updateMovieForm.html")
    public String updateMovieForm(Model model, MovieDTO movieDTO) {
        moviesService.updateMovie(movieDTO);
        return "movieForm";
    }

    @PostMapping("/deleteMovieForm.html")
    public String deleteMovieForm(Model model, MovieDTO movieDTO) {
        moviesService.deleteMovie(movieDTO.getId());
        return "redirect:getMovies.html";
    }

/*    @GetMapping("/getOneMovie.html")
    public String getOneMovie(@ModelAttribute("movieId") Integer movieId, Model model) {
        model.addAttribute("movie", moviesService.getOneMovie(movieId));
        return "movies";
    }

    @GetMapping("/saveNewMovie.html")
    public String showScreenForNewMovie(Model model) {
        model.addAttribute("colorsList", MovieAgeRatingEnum.values());
        model.addAttribute("engineTypes", MovieGenreEnum.values());
        model.addAttribute("newMovieDTO", new MovieDTO());
        return "moviesForm";
    }

    @PostMapping("/saveNewMovie.html")
    public String saveNewMovie(@ModelAttribute MovieDTO movieDTO, Model model) {
        model.addAttribute("newMovieDTO", movieDTO);
        moviesService.addNewMovie(movieDTO);
        return "redirect:getMovies.html";
    }*/

    private void addEnumsToModel(Model model) {
        if (!model.containsAttribute("engineTypes"))
            model.addAttribute("movieGenres", MovieGenreEnum.values());
        if (!model.containsAttribute("colors"))
            model.addAttribute("movieAgeRatings", MovieAgeRatingEnum.values());
    }
}
