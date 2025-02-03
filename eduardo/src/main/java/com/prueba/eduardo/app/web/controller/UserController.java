package com.prueba.eduardo.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.eduardo.app.service.UserService;
import com.prueba.eduardo.app.web.model.UserModel;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "")
	public ResponseEntity<UserModel> getUserById(@PathVariable Long id){
		return ResponseEntity.ok().body(userService.findFyId(id));
	}
	
	@PostMapping
	@ApiOperation(value = "Registrar un nuevo usuario")
	public ResponseEntity<UserModel> saveUser(@RequestBody UserModel model){
		return ResponseEntity.ok().body(userService.saveUser(model));
	}
	
	@PatchMapping("/{id}")
	@ApiOperation(value = "Actualizar información del usuario")
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel model, @PathVariable Long id){
		return ResponseEntity.ok().body(userService.updateUser(model, id));
	}
}
