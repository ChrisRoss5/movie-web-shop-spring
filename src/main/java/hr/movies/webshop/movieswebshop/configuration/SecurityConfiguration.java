package hr.movies.webshop.movieswebshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/mvc/movieswebshop/createMovie.html",
                                "/mvc/movieswebshop/updateMovie.html",
                                "/mvc/movieswebshop/deleteMovie.html",
                                "/mvc/movieswebshop/createMovieGenre.html",
                                "/mvc/movieswebshop/updateMovieGenre.html",
                                "/mvc/movieswebshop/deleteMovieGenre.html",
                                "/mvc/movieswebshop/getLogs.html",
                                "/mvc/movieswebshop/searchHistory.html",
                                "/h2-console/**"
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                "/rest/movieswebshop/purchase",
                                "/mvc/movieswebshop/getPurchase.html",
                                "/mvc/movieswebshop/getHistory.html"
                        )
                        .hasAnyRole("USER", "ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.defaultSuccessUrl("/"))
                .logout(logout -> logout.logoutSuccessUrl("/"));
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    @Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        UserDetails readOnlyUser = users
                .username("read_only")
                .password("password")
                .roles("READ_ONLY")
                .build();
        return new InMemoryUserDetailsManager(user, admin, readOnlyUser);
    }
     */
}
