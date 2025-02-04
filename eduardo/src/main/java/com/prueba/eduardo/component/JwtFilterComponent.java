package com.prueba.eduardo.component;

import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.prueba.eduardo.app.service.SessionService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilterComponent extends OncePerRequestFilter {

    private final JwtTokenProviderComponent jwtTokenProvider;
    private final SessionService sessionService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
        String token = getTokenFromRequest(request);

        if (token != null) {
            // Validar el token
            boolean isTokenValid = jwtTokenProvider.validateToken(token);

            if (isTokenValid) {
                // Verificar si la sesión está activa
                if (sessionService.isTokenValid(token)) {
                    // Extraer el nombre de usuario del token y autenticación
                    String username = jwtTokenProvider.parseToken(token).getSubject();
                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    // Invalida la sesión si no es válida
                    sessionService.invalidateSession(token);
                    throw new ServletException("Token inválido o sesión cerrada");
                }
            } else {
                // Si el token es inválido, inválida la sesión y lanza una excepción
                sessionService.invalidateSession(token);
                throw new ServletException("Token inválido o sesión cerrada");
            }
        }

        // Continuar con el filtro si todo es correcto
        filterChain.doFilter(request, response);
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null) {
            return bearerToken;
        }
        return null;
    }
}

