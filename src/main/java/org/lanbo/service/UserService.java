package org.lanbo.service;

import org.lanbo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void save(User user);
    User show(Long id);
    void update(Long id, User updatedUser);
    void delete(Long id);
}
