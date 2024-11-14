package klu.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import klu.model.User;
import klu.repo.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/signup", "/user/signup", "/aboutus", "/contactus").permitAll()
                .requestMatchers("/admin", "/createfund").hasRole("admin") // Only admin can access /admin
                .anyRequest().authenticated() // All other requests require authentication
            )
            .formLogin(login -> login
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    // Redirect based on role
                    String role = authentication.getAuthorities().iterator().next().getAuthority();
                    if (role.equals("ROLE_admin")) {
                        response.sendRedirect("/createfund");
                    } else {
                        response.sendRedirect("/home");
                    }
                })
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/logout") // URL to trigger logout
                .logoutSuccessUrl("/login?logout=true") // Redirect after successful logout
                .invalidateHttpSession(true) // Invalidate the session
                .clearAuthentication(true) // Clear authentication
                .permitAll())
            .csrf().and(); // Ensure CSRF protection is enabled

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<User> userOptional = userRepository.findByUsername(username);
            User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password("{noop}" + user.getPassword()) // NoOp for plaintext password
                    .roles(user.getRole())
                    .build();
        };
    }

}
