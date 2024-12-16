package ufrn.imd.FallBank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // enables POST, DELETE, PUT via Postman
            .httpBasic(Customizer.withDefaults()) // allows HTTP Basic authentication
            .formLogin(Customizer.withDefaults()) // enables login forms
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/banco/public/**").permitAll() // public access
                .requestMatchers("/banco/admin/**").hasAuthority("ROLE_ADMIN") // ADMIN only
                .requestMatchers("/banco/private/**").hasRole("USER") // USER only
                .requestMatchers("/login", "/logout", "/error").permitAll() 
                .requestMatchers("/Bank/login", "/Bank/logout").permitAll() // enables access to log in and log out
            );
    
        return http.build();
    }
    @Bean
    public UserDetailsService users() {
        PasswordEncoder encoder = passwordEncoder(); // gets PasswordEncoder
        UserBuilder users = User.builder().passwordEncoder(encoder::encode); // adds encoder

        UserDetails user = users
            .username("user")
            .password("user") // encodes automatically
            .roles("USER")
            .build();

        UserDetails admin = users
            .username("admin")
            .password("admin") // encodes automatically
            .roles("USER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // safe encoder
    }
}
