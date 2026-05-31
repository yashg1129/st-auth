package com.st.auth.service.impl;

import com.st.auth.entity.User;
import com.st.auth.exception.UserNotFoundException;
import com.st.auth.repository.UserRepository;
import com.st.auth.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) {
        User dbUser = repository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User doesn't exists"));
        copy(user, dbUser);
        return repository.save(dbUser);
    }

    void copy(User from, User to) {
        to.setPassword(from.getPassword());
        to.setName(from.getName());
        to.setAddress(from.getAddress());
        to.setExperience(from.getExperience());
        to.setGender(from.getGender());
        to.setMobile(from.getMobile());
    }

    @Override
    public User updatePassword(Integer userId, String password) {
        return null;
    }
}
