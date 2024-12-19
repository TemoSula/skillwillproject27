package com.example.skillwillproject27.Filters;

import com.example.skillwillproject27.Models.UserModel;
import com.example.skillwillproject27.Repositories.UserRepository;
import com.example.skillwillproject27.Services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Qualifier("customAuthFilter")
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String AuthHeader = request.getHeader("Authorization");
        String token = "";

        if(AuthHeader == null)
        {
            filterChain.doFilter(request,response);
            return;
        }

        if(AuthHeader != null && AuthHeader.startsWith("Bearer "))
        {
            token = AuthHeader.substring(7);
        }
        if(jwtService.isExpiredToken(token))
        {
            System.out.println("token is expired");
            filterChain.doFilter(request,response);
        }

        Claims clm = jwtService.getAllClaims(token);
        String username = (String)clm.get("username");
        UserModel userModel = userRepository.findByUsername(username);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userModel.getUsername(),userModel.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_"+userModel.getRole())));
        if(SecurityContextHolder.getContext().getAuthentication() == null)
        {
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }else
        {
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request,response);

    }
}
