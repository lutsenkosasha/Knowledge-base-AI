package org.example.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.service.CustomUserDetailsService;
import org.example.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("Extracted token: " + token);
            try {
                var claims = jwtUtil.extractClaims(token);
                String email = claims.getSubject();
                Boolean isAdmin = claims.get("isAdmin", Boolean.class);
                System.out.println("Extracted email: " + email + ", isAdmin: " + isAdmin);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var userDetails = userDetailsService.loadUserByUsername(email);

                    if (claims.getExpiration().after(new java.util.Date())) {
                        List<GrantedAuthority> authorities =
                                new java.util.ArrayList<>(userDetails.getAuthorities());

                        // Если isAdmin true — добавляем роль
                        if (Boolean.TRUE.equals(isAdmin)) {
                            authorities.add(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_ADMIN"));
                        }

                        var authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, authorities);
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        System.out.println("Authentication set for user: " + email);
                    } else {
                        System.out.println("Token has expired.");
                    }
                } else {
                    System.out.println("Email not found or authentication already set.");
                }
            } catch (Exception e) {
                System.out.println("JWT validation failed: " + e.getMessage());
            }
        } else {
            System.out.println("No Bearer token found in Authorization header.");
        }

        filterChain.doFilter(request, response);
    }
}
