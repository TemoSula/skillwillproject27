package com.example.skillwillproject27.SecurityConfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class Config {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, @Qualifier("customAuthFilter") OncePerRequestFilter oncefilter) throws Exception {
        return http.csrf(cus -> cus.disable())
                .addFilterBefore(oncefilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(custom -> custom.requestMatchers("/login","/registration",
                        "/swagger-ui/**","/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui.html", "/webjars/**").permitAll().anyRequest().authenticated())
                .build();
    }


    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
