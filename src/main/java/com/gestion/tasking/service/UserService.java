package com.gestion.tasking.service;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.gestion.tasking.entity.User;

import com.gestion.tasking.repository.UserRepository;

@Service
public class UserService {
	

	@Autowired
	private UserRepository userRepository;
	
	
	
	public User registerUser(String nombre, String email,String password) {
		
		if(userRepository.findByEmail(email).isPresent()) {
			
			throw new  RuntimeException("El usuario ya existe");
		}
		
		User user = new User();
		user.setEmail(email);
		user.setFecha_creacion(LocalDateTime.now());
		user.setNombre(nombre);
		user.setPassword(password);

		return userRepository.save(user);
		
		
	}
	  
    public boolean checkUserExists(String email) {
        return userRepository.existsByEmail(email); 
    }
	
    public boolean verifyPassword(String email, String password) {
        
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt
                .map(user -> user.getPassword().equals(password)) 
                .orElse(false); 
    }
	

}
