package com.prueba.eduardo.app.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.eduardo.app.domain.entity.User;

/**
 * Repositorio para consultas de usuario
 * 
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
}
