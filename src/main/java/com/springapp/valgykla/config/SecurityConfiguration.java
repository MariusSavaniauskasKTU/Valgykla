package com.springapp.valgykla.config;


import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.springapp.valgykla.Model.Permission.USER_READ;
import static com.springapp.valgykla.Model.Permission.WORKER_READ;
import static com.springapp.valgykla.Model.Role.*;
import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/vi/auth/**", "/static/**")
                .permitAll()
                //.requestMatchers("/meniu/**").hasAnyRole(ADMIN.name() ,WORKER.name())
                //.requestMatchers("/ingredient/**").hasAnyRole(ADMIN.name() ,WORKER.name())
                //.requestMatchers("/dish/**").hasAnyRole(ADMIN.name() ,WORKER.name())
                //.requestMatchers(GET,"/meniu/**").hasAnyAuthority(WORKER_READ.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
