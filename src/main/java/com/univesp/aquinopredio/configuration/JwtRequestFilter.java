package com.univesp.aquinopredio.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    private static final List<String> IGNORING_PATHS = Arrays.asList(
            "/auth/login",
            "/pets",
            "/pets/**",
            "/posts",
            "/posts/",
            "/posts/**",
            "/services",
            "/services/",
            "/services/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    );

    public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        logger.info("Processing request: Method={}, URI={}", method, requestURI);

        if (isIgnoringPath(requestURI) && (method.equals("GET") || method.equals("OPTIONS"))) {
            logger.info("Ignoring path and method: Method={}, URI={}", method, requestURI);
            chain.doFilter(request, response);
            return;
        }

        if (requestURI.startsWith("/posts/") && method.equals("GET")) {
            logger.info("Permitting GET for specific post URI: {}", requestURI);
            chain.doFilter(request, response);
            return;
        }


        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            logger.info("Authorization Header found. Extracted JWT: {}", jwt);
            try {
                username = jwtUtil.extractUsername(jwt);
                logger.info("Username extracted from JWT: {}", username);
            } catch (Exception e) {
                logger.error("Error extracting username from token: {}", e.getMessage());
            }
        } else {
            logger.warn("Authorization Header is missing or does not start with Bearer for URI: {}", requestURI);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            try {
                userDetails = this.userDetailsService.loadUserByUsername(username);
                logger.info("User details loaded for username: {}", username);
            } catch (Exception e) {
                logger.error("Error loading user details for username {}: {}", username, e.getMessage());
            }


            if (userDetails != null) {
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    logger.info("Token validated successfully for username: {}", username);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    logger.info("User authenticated in SecurityContextHolder: {}", username);
                } else {
                    logger.warn("Token validation failed for username: {}", username);
                }
            } else {
                logger.warn("User details not found for username: {}", username);
            }
        } else {
            logger.info("Username is null or user already authenticated. Username: {}, Authenticated: {}",
                    username, SecurityContextHolder.getContext().getAuthentication() != null);
        }

        chain.doFilter(request, response);
    }

    private boolean isIgnoringPath(String requestURI) {
        for (String ignoringPath : IGNORING_PATHS) {

            if (requestURI.matches(ignoringPath.replace("**", ".*").replace("*", "[^/]*"))) {
                return true;
            }
        }
        return false;
    }
}
