package com.prueba.eduardo.app.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.eduardo.app.service.AuthenticationService;
import com.prueba.eduardo.app.web.model.AuthResponseModel;
import com.prueba.eduardo.app.web.model.LoginRequest;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    /**
     * Método para iniciar sesión
     * @param loginRequest
     * @param authorizationHeader
     * @return
     */
    @ApiOperation(value = "logeo")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest,
    		@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        try {
           
            String token = authenticationService.login(loginRequest.getUserName(), loginRequest.getPassword());
            return ResponseEntity.ok(new AuthResponseModel(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    
    /**
     * Método para cerrar sesión
     * @param authorizationHeader
     * @return
     */
    @ApiOperation(value = "Logout")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader) {
    	return ResponseEntity.ok().body(authenticationService.logout(authorizationHeader));
    }

}
