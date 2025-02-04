package com.prueba.eduardo.app.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prueba.eduardo.app.domain.entity.User;
import com.prueba.eduardo.component.JwtTokenProviderComponent;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Secicio de autenticación
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserService userService;

    private final JwtTokenProviderComponent jwtTokenProvider;
    
    private final SessionService sessionService;
    

    /**
     * Método para iniciar sesión
     * @param username
     * @param password
     * @return
     */
    public String login(String username, String password) {
        User usuario = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (sessionService.hasActiveSession(usuario.getId())) {
            throw new IllegalStateException("Ya existe una sesión activa.");
        }

        if (!password.matches(usuario.getPassword())) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        String token = jwtTokenProvider.createToken(usuario);

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5); 

        sessionService.createSession(usuario.getId(), token, expirationTime);

        return token;
    }
    
    /**
     * Método para cerrar sesión
     * @param token
     * @return
     */
    public String logout(String token) {
        try {
            sessionService.invalidateSession(token);
            return "Sesión cerrada exitosamente.";
        } catch (Exception e) {
        	log.info(e.getMessage());
            return "Error al cerrar la sesión: " + e.getMessage();
        }
    }

}
