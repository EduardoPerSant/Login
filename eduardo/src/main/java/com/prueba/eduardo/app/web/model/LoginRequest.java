package com.prueba.eduardo.app.web.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * Modelo para inciar sesión
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Getter
@RequiredArgsConstructor
public class LoginRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String password;

}
