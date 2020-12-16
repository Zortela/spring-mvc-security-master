package org.lanbo.service;

import org.lanbo.dao.UserDAO;
import org.lanbo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public User show(Long id) {
        return userDAO.show(id);
    }

    @Override
    public void update(Long id, User updatedUser) {
        userDAO.update(id, updatedUser);
    }

    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }

}
