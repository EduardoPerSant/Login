package com.prueba.eduardo.app.web.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.prueba.eduardo.app.domain.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Getter
@Setter
@RequiredArgsConstructor
public class UserModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "user name", example = "usuario1")
	private String username;
	
	@ApiModelProperty(value = "id", example = "*******")
	private String password;
	
	@ApiModelProperty(value = "emil", example = "example@example.com")
	private String email;
	
	@ApiModelProperty(value = "role", example = "default")
	private String role;
	
	@ApiModelProperty(value = "active", example = "true")
	private boolean active;
	
	@ApiModelProperty(value = "id", example = "01-01-1999")
	private Date creationDate;
	
	@ApiModelProperty(value = "id", example = "01-01-1999")
	private Date lastDateModify;
	
	/**
	 * 
	 * @return
	 */
	public User toEntity() {
		User entity = new User();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}
}
