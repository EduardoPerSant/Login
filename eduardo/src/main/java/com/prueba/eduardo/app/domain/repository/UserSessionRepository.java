package com.prueba.eduardo.app.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.eduardo.app.domain.entity.UserSession;

/**
 * Repositorio para manejo de consultas a la tabla userSession
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

	/**
	 * Método para encontrar una sesión por su token
	 * @param token
	 * @return
	 */
    Optional<UserSession> findByToken(String token);

    /**
     * Método para encontrar una sesión activa de un usuario
     * @param userId
     * @return
     */
    Optional<UserSession> findByUserIdAndIsActiveTrue(Long userId);
    
    /**
     * Método para invalidar una sesión
     * @param token
     */
    void deleteByToken(String token);
    
    /**
     * eliminar sesionces por fecha de expiración pasada
     * @param now
     */
    void deleteByExpiresAtBeforeAndIsActiveTrue(LocalDateTime now);
}
