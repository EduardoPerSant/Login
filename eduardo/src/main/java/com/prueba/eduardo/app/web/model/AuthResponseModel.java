package com.prueba.eduardo.app.web.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Modelo de respuesta al inicio de sesión
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Getter
@NoArgsConstructor
public class AuthResponseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String token;
	
	public AuthResponseModel(String token){
		this.token = token;
	}
	
	
}
