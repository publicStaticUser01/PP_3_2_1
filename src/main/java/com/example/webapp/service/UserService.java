package com.example.webapp.service;

import com.example.webapp.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void createUser(User user);

    User getUserById(Long id);

    void editUser(Long id, User user);

    void deleteUser(long id);

}
