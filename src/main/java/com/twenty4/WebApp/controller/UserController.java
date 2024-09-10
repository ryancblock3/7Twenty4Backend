package com.twenty4.WebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twenty4.WebApp.model.Credentials;
import com.twenty4.WebApp.model.LoginResponse;
import com.twenty4.WebApp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Credentials credentials) {
        String token = userService.authenticateUser(credentials.getUsername(), credentials.getPassword());
        if (token != null) {
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}