package com.prueba.eduardo.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.eduardo.app.domain.entity.UserSession;
import com.prueba.eduardo.app.domain.repository.UserSessionRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service para el manejo de sesion
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Service
@RequiredArgsConstructor
public class SessionService {

    private final UserSessionRepository userSessionRepository;

    /**
     * Crear una nueva sesión
     * @param userId
     * @param token
     * @param expiresAt
     * @return
     */
    public UserSession createSession(Long userId, String token, LocalDateTime expiresAt) {
        UserSession session = new UserSession();
        session.setUserId(userId);
        session.setToken(token);
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(expiresAt);
        session.setActive(true);
        return userSessionRepository.save(session);
    }

    /**
     * Verificar si un token JWT es válido
     * @param token
     * @return
     */
    public boolean isTokenValid(String token) {
        Optional<UserSession> sessionOpt = userSessionRepository.findByToken(token);
        if (sessionOpt.isPresent()) {
            UserSession session = sessionOpt.get();
            return session.isActive() && session.getExpiresAt().isAfter(LocalDateTime.now());
        }
        return false;
    }

    /**
     *  Invalidar una sesión por el token
     * @param token
     */
    public void invalidateSession(String token) {
        Optional<UserSession> sessionOpt = userSessionRepository.findByToken(token);
        sessionOpt.ifPresent(session -> {
            session.setActive(false);
            userSessionRepository.save(session);
        });
    }

    /**
     * Verificar si el usuario tiene una sesión activa
     * @param userId
     * @return boolean
     */
    public boolean hasActiveSession(Long userId) {
        Optional<UserSession> sessionOpt = userSessionRepository.findByUserIdAndIsActiveTrue(userId);
        return sessionOpt.isPresent();
    }
}
