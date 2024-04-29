package hr.movies.webshop.movieswebshop.controller.mvc;

import hr.movies.webshop.movieswebshop.model.HistorySearchForm;
import hr.movies.webshop.movieswebshop.model.User;
import hr.movies.webshop.movieswebshop.repository.UserRepository;
import hr.movies.webshop.movieswebshop.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mvc/movieswebshop")
@AllArgsConstructor
@SessionAttributes({"historySearchForm", "users"})
public class HistoryController {

    private PurchaseService purchaseService;
    private UserRepository userRepository;

    @GetMapping("/getHistory.html")
    public String getHistory(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));
        if (isAdmin) {
            if (!model.containsAttribute("users"))
                model.addAttribute("users", userRepository.findAll());
            if (!model.containsAttribute("historySearchForm"))
                model.addAttribute("historySearchForm", new HistorySearchForm());
            model.addAttribute("purchases", purchaseService.filterHistory(
                    (HistorySearchForm) model.getAttribute("historySearchForm")));
        } else
            model.addAttribute("purchases", purchaseService.getHistory(user));
        return "history";
    }

    @PostMapping("/searchHistory.html")
    public String searchHistory(Model model, HistorySearchForm historySearchForm,
                                @RequestParam(name = "clearFilters", required = false) String clearFilters) {
        if (clearFilters != null) historySearchForm = new HistorySearchForm();
        model.addAttribute("historySearchForm", historySearchForm);
        return "redirect:getHistory.html";
    }
}
