package com.prueba.eduardo.app.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.eduardo.app.domain.entity.User;
import com.prueba.eduardo.app.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFacade {

	private final UserRepository repository;
	
	
	/**
	 * find user by id
	 * @param id
	 * @return { @link User }
	 */
	public User findById(Long id) {
		return repository.getById(id);
	}
	
	/**
	 * find all user
	 * @return list of { @link User }
	 */
	public List<User> findAll(){
		return repository.findAll();
	}
	
	/**
	 * save a new user
	 * @param user
	 * @return instance of { @link User }
	 */
	@Transactional(readOnly = false)
	public User saveUser(User user) {
		return repository.save(user);
	}
	
	/**
	 * update User
	 * @param instance of { @link User }
	 * @return
	 */
	public User updateUser(User user) {
		return repository.save(user);
	}

	/**
	 * find user by name
	 * @param username
	 * @return
	 */
	public Optional<User> findByUserName(String username) {
		return repository.findByUsername(username);
	}
}
