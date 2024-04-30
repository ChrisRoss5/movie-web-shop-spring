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

    @Override
    public void sessionCreated(final HttpSessionEvent event) {
        System.out.println("Session created");
        String ipAddr = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr();
        System.out.println("IP address: " + ipAddr);

        // NOT GUARANTEED TO WORK BECAUSE SESSION MAY BE CREATED BEFORE OR AFTER LOGIN:
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // LOGS MOVED TO LoginListener.java
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        System.out.println("Session destroyed");
    }

}
