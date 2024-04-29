package hr.movies.webshop.movieswebshop.controller.mvc;

import hr.movies.webshop.movieswebshop.repository.LogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc/movieswebshop")
@AllArgsConstructor
public class LogsController {

    private LogRepository logRepository;

    @GetMapping("/getLogs.html")
    public String getLogs(Model model) {
        model.addAttribute("logs", logRepository.findAll().reversed());
        return "logs";
    }
}
