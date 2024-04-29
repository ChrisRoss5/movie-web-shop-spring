package hr.movies.webshop.movieswebshop.listener;

import hr.movies.webshop.movieswebshop.model.Log;
import hr.movies.webshop.movieswebshop.model.User;
import hr.movies.webshop.movieswebshop.repository.LogRepository;
import hr.movies.webshop.movieswebshop.repository.UserRepository;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Component
public class HttpSessionMoviesWebShopListener implements HttpSessionListener {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void sessionCreated(final HttpSessionEvent event) {
        System.out.println("Session created");
        String ipAddr = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr();
        System.out.println("IP address: " + ipAddr);
        System.out.println("User: " + SecurityContextHolder.getContext().getAuthentication());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        logRepository.save(new Log(null, user, ipAddr, "New session created", LocalDateTime.now()));
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        System.out.println("Session destroyed");
    }

}
