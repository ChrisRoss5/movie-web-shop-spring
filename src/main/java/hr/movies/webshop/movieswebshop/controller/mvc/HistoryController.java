package hr.movies.webshop.movieswebshop.controller.mvc;

import hr.movies.webshop.movieswebshop.model.User;
import hr.movies.webshop.movieswebshop.repository.UserRepository;
import hr.movies.webshop.movieswebshop.service.MoviesService;
import hr.movies.webshop.movieswebshop.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc/movieswebshop")
@AllArgsConstructor
public class HistoryController {

    private PurchaseService purchaseService;
    private UserRepository userRepository;

    @GetMapping("/getHistory.html")
    public String getHistory(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        model.addAttribute("purchases", purchaseService.getPurchaseHistory(user));
        return "history";
    }
}
