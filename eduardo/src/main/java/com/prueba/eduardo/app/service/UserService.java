package com.prueba.eduardo.app.service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.prueba.eduardo.app.domain.entity.User;
import com.prueba.eduardo.app.facade.UserFacade;
import com.prueba.eduardo.app.web.model.UserModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Servicio para manejo de los datos del usuario
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@RequiredArgsConstructor
@Service
@Log4j2
public class UserService {
	
	private final UserFacade userFacade;

	/**
	 * Método para buscar por id usuario
	 * @param id
	 * @return
	 */
	public UserModel findFyId(Long id) {
		User response = new User();
		try {
			response = userFacade.findById(id);
			return response.toModel();
		}catch(EntityNotFoundException ex) {
			log.error(ex.getMessage());
		}
		return null;
	}


	/**
	 * Método para buscar por nombre de usuario
	 * @param username
	 * @return
	 */
	public Optional<User> findByUsername(String username) {
		return userFacade.findByUserName(username);
	}
	
	/**
	 * Método para registrar usuario
	 * @param model
	 * @return
	 */
	public UserModel saveUser(UserModel model) {
		User entity =  model.toEntity();
		entity.setActive(true);
		entity.setRole("default");
		entity.setCreationDate(new Date());
		entity = userFacade.saveUser(entity);
		if(Objects.isNull(entity)) {
			log.info("Error al crear el usuario.");
		}
		return entity.toModel();
	}
	
	/**
	 * Método para actualizar información del usuario
	 * @param model
	 * @param id
	 * @return
	 */
	public UserModel updateUser(UserModel model, Long id) {
		User entity = userFacade.findById(id);
		entity.setActive(model.isActive());
		entity.setRole(model.getRole());
		entity.setLastUpdated(new Date());
		entity = userFacade.saveUser(entity);
		if(Objects.isNull(entity)) {
			log.info("Error al actualizar el usuario.");
		}
		return entity.toModel();
	}
	
	/**
	 * Método para eliminar usuario
	 * @param id
	 * @throws Exception
	 */
	public void deleteUser(Long id) throws Exception {
		User entity = userFacade.findById(id);
		entity.setActive(false);
		entity = userFacade.saveUser(entity);
		if(Objects.isNull(entity)) {
			log.info("Error al eliminar el usurio.");
			throw new Exception("Error al eliminar el usurio.");
		}
	}
}
