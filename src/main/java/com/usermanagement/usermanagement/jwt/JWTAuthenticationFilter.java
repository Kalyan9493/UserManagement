package com.usermanagement.usermanagement.jwt;

import com.usermanagement.usermanagement.model.Role;
import com.usermanagement.usermanagement.model.User;
import com.usermanagement.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userName;
        if (request.getRequestURI().startsWith("/swagger") || request.getRequestURI().startsWith("/v2/api-docs")) {
            // Allow access without authentication
            filterChain.doFilter(request, response);
            return;
        }
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);
        userName = jwtService.extractUsername(jwtToken);
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User userDetails = userRepository.findUserByEmailOrMobile(userName,null);
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Role role : userDetails.getRoles()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            }
            if(jwtService.isTokenValid(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        authorities
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
