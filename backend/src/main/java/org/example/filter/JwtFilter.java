package org.example.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.service.CustomUserDetailsService;
import org.example.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

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
                // Получаем claims и вытаскиваем subject (email)
                var claims = jwtUtil.extractClaims(token);
                String email = claims.getSubject();
                System.out.println("Extracted email: " + email);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    System.out.println("User email found, loading user details...");
                    var userDetails = userDetailsService.loadUserByUsername(email);
                    System.out.println("User details loaded: " + userDetails);
                    // Проверяем срок действия токена
                    if (claims.getExpiration().after(new java.util.Date())) {
                        System.out.println("Token is valid, setting authentication...");
                        var authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        System.out.println("Authentication set for user: " + email);
                    } else {
                        System.out.println("Token has expired.");
                    }
                } else {
                    System.out.println("Email not found or authentication already set."); // Логирование, если email не найден или аутентификация уже установлена
                }
            } catch (Exception e) {
                // В случае любого фейла просто пропускаем фильтр
                System.out.println("JWT validation failed: " + e.getMessage());
            }
        } else {
            System.out.println("No Bearer token found in Authorization header."); // Логирование, если токен не найден в заголовке
        }

        filterChain.doFilter(request, response);
    }
}
