package com.twenty4.WebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.twenty4.WebApp.service.UserService;

@SpringBootApplication
public class WebAppApplication {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        userService.createEvaluatorUser();
    }
}