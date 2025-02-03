package com.prueba.eduardo.app.service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.prueba.eduardo.app.domain.repository.UserSessionRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service para limpiar sesiones expiradas
 * @author Eduardo Pérez
 * @since FEb 03, 2025
 */
@Service
@RequiredArgsConstructor
public class SessionCleanupService {

    private final UserSessionRepository userSessionRepository;

    // Elimina sesiones expiradas cada 24 horas
    @Scheduled(cron = "0 0 0 * * ?")  // Ejemplo: se ejecuta cada día a la medianoche
    public void cleanupExpiredSessions() {
        LocalDateTime now = LocalDateTime.now();
        userSessionRepository.deleteByExpiresAtBeforeAndIsActiveTrue(now);
    }
}
