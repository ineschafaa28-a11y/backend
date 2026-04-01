package com.Back.BackEnd.service;

import com.Back.BackEnd.model.User;
import com.Back.BackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ Méthode d’inscription
    public User register(User user) {
        // Vérifier si l’email existe déjà
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email déjà utilisé !");
        }
        return userRepository.save(user);
    }

    // ✅ Méthode de connexion simple
    public boolean login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
}
