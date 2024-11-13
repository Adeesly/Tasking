package com.gestion.tasking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.tasking.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail (String email);
	
	
    // Método para verificar si el usuario existe
    boolean existsByEmail(String email);


}
