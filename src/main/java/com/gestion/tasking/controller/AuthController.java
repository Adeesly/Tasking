package com.gestion.tasking.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.gestion.tasking.entity.User;
import com.gestion.tasking.model.AuthResponse;
import com.gestion.tasking.model.LoginRequest;
import com.gestion.tasking.service.AuthService;
import com.gestion.tasking.service.UserService;

@RestController
@RequestMapping("/api/user")
public class AuthController {
	
	@Autowired
	private UserService userService;
	  @Autowired
	    private AuthService authService;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody User user){
		Map<String, String> response = new HashMap<>();
		
		try {
			
			userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
			
			response.put("mensaje", "Usuario Registrado Correctamente ");
			return ResponseEntity.ok(new AuthResponse(0, "Usuario Creado Existosamente"));
			
		} catch (Exception e) {
		
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            		.body(new AuthResponse(1, "Error: Usuario ya existe o es invalido"));
		}
		
	}
	
	 @PostMapping("/login")
	    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
	     
	        boolean userExists = userService.checkUserExists(loginRequest.getEmail());
	        boolean passwordCorrect = userService.verifyPassword(loginRequest.getEmail(), loginRequest.getPassword());

	        if (!userExists) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            		.body(new AuthResponse(1, "Usuario no existe o es invalido"));
	        }

	        if (!passwordCorrect) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new AuthResponse(1, "La cedenciales son incorrecta"));
	        }

	        
	        
	        // Si el login es exitoso
	        return ResponseEntity.ok(new AuthResponse(0, "succes"));
	    }
	
	

}
