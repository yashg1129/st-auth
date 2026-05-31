package com.st.auth.service;

import com.st.auth.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    User update(User user);

    User updatePassword(Integer userId, String password);

}
