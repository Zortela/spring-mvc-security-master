package org.lanbo.dao;


import org.lanbo.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("from User");
        List<User> result = query.getResultList();
        return result;

    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User show(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(Long id, User updatedUser) {
        User user = entityManager.find(User.class, id);
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setSurname(updatedUser.getSurname());
        user.setAge(updatedUser.getAge());
        user.setEmail(updatedUser.getEmail());
        entityManager.flush();

    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User loadUserByUserName(String username) {
        Query query = entityManager.createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();
        return user;
    }
}
