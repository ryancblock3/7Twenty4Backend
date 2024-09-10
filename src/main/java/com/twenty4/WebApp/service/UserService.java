package com.twenty4.WebApp.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twenty4.WebApp.entity.User;
import com.twenty4.WebApp.repository.UserRepository;

import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String authenticateUser(String username, String password) {
        logger.info("Attempting to authenticate user: {}", username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.warn("User not found: {}", username);
            return null;
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            logger.info("User {} authenticated successfully", username);
            String token = generateToken(user);
            logger.info("Generated token for user {}: {}", username, token);
            return token;
        }
        logger.warn("Invalid password for user: {}", username);
        return null;
    }

    private String generateToken(User user) {
        long expirationTime = 86400000;
        Date expiration = new Date(System.currentTimeMillis() + expirationTime);

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();
        return token;
    }

    @PostConstruct
    public void createEvaluatorUser() {
        User existingEvaluator = userRepository.findByUsername("evaluator");
        if (existingEvaluator != null) {
            logger.info("Evaluator user already exists: {}", existingEvaluator);
            return;
        }
        String plainTextPassword = "wgu123!";
        String hashedPassword = passwordEncoder.encode(plainTextPassword);

        User evaluator = new User();
        evaluator.setUsername("evaluator");
        evaluator.setPassword(hashedPassword);
        evaluator.setRole(User.Role.EVALUATOR);
        evaluator.setCreatedAt(LocalDateTime.now());
        evaluator.setUpdatedAt(LocalDateTime.now());
        evaluator.setEmail("evaluator@example.com");

        userRepository.save(evaluator);
        logger.info("Evaluator user created successfully: {}", evaluator);
    }
}