package com.st.auth.controller;

import com.st.auth.entity.User;
import com.st.auth.service.UserService;
import com.st.auth.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public User getUser() {
        Integer userId = SecurityUtils.getUserId();
        return service.findById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<User> getAllUser() {
        Integer userId = SecurityUtils.getUserId();
        return service.findAll();
    }

    @PutMapping
    public User update(@RequestBody User user) {
        Integer userId = SecurityUtils.getUserId();
        user.setId(userId);
        return service.update(user);
    }
}
