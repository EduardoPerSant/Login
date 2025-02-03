package com.prueba.eduardo.app.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.prueba.eduardo.app.web.model.UserModel;

import lombok.Getter;
import lombok.Setter;

/** 
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Getter
@Setter
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	@Column(name = "last_updated")
	private Date lastUpdated;
	
	
	/**
	 * 
	 * @return instace of { @link UserModel }
	 */
	public UserModel toModel() {
		UserModel model = new UserModel();
		BeanUtils.copyProperties(this, model);
		return model;
	}
	
}
