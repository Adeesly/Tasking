package com.gestion.tasking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.tasking.entity.User;
import com.gestion.tasking.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateUserCredentials(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        // Verificar si el usuario existe y la contraseña es correcta (sin encriptación)
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
