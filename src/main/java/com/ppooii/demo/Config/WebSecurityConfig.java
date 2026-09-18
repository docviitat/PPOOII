package com.ppooii.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ppooii.demo.Config.Model.JwtConstants.LOGIN_URL;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Login endpoint must be open, plus CORS pre-flight requests
                .requestMatchers(LOGIN_URL).permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Actual public endpoints, matching PublicController's real base path (/api/public/**)
                // and PersonaController's public sub-path.
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/personas/public/**").permitAll()

                // Everything else (admin CRUD, usuario management) requires a valid JWT + APIKey
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
