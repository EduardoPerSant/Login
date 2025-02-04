package com.prueba.eduardo.app.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.prueba.eduardo.app.domain.entity.User;
import com.prueba.eduardo.app.facade.UserFacade;
import com.prueba.eduardo.app.web.model.DocumentModel;
import com.prueba.eduardo.app.web.model.RequestDocumentModel;
import com.prueba.eduardo.app.web.model.UserModel;

import io.jsonwebtoken.lang.Collections;
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
	
	private final DocumentService documentService;

	/**
	 * Método para buscar por id usuario
	 * @param id
	 * @return
	 */
	public UserModel findById(Long id) {
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
	
	/**
	 * find Document by id
	 * @param id
	 * @return instance of { {@link DocumentModel }
	 */
	public DocumentModel findDocumentById(Long id) {
		return documentService.getById(id);
	}
	
	/**
	 * get list of document by user
	 * @param id
	 * @return list of { {@link DocumentModel}
	 */
	public List<DocumentModel> findDocumentByUser(Long id){
		log.info("find by user id");
		UserModel model = this.findById(id);
		if(Objects.isNull(model)) {
			return Collections.emptyList();
		}
		return documentService.findAllByUser(id);
	}
	
	/**
	 * save new Document
	 * @param model
	 * @return instance of { {@link DocumentModel }
	 */
	public DocumentModel saveDocument(RequestDocumentModel request) throws Exception {
		UserModel model = this.findById(request.getUserId());
		if(Objects.isNull(model)) {
			throw new Exception("Usuario no encontrado.");
		}
		documentService.storeFiles(request.getFiles(), request.getUserId());
		return new DocumentModel();
	}
	
}
