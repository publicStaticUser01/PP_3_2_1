package com.example.webapp.dao;

import com.example.webapp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM users u", User.class);
        return query.getResultList();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        return user;
    }

    @Override
    public void editUser(Long id, User user) {
        User userToBeEdit = getUserById(id);
        userToBeEdit.setName(user.getName());
        userToBeEdit.setLastName(user.getLastName());
        userToBeEdit.setEmail(user.getEmail());
        entityManager.merge(userToBeEdit);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }


}
