package com.Back.BackEnd.controller;

import com.Back.BackEnd.model.User;
import com.Back.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // ✅ Endpoint pour l’inscription
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User newUser = userService.register(user);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // ✅ Endpoint pour le login simple
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password) {
        boolean success = userService.login(username, password);
        if (success) {
            return ResponseEntity.ok("Connexion réussie !");
        } else {
            return ResponseEntity.status(401).body("Nom d’utilisateur ou mot de passe incorrect !");
        }
    }
}

