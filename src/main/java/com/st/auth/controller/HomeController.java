package com.st.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/api/user")
    public ResponseEntity<String> home1() {
        return ResponseEntity.ok("Welcome, authenticated user1");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin")
    public ResponseEntity<String> home2() {
        return ResponseEntity.ok("Welcome, authenticated user2");
    }

    @PreAuthorize("hasAuthority('QUESTION_APPROVE')")
    @GetMapping("/api/approve")
    public ResponseEntity<String> home3() {
        return ResponseEntity.ok("Welcome, authenticated user3");
    }
}