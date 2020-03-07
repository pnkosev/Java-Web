package repository;

import domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        this.entityManager.getTransaction().begin();

        this.entityManager.persist(user);

        this.entityManager.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        this.entityManager.getTransaction().begin();

        this.entityManager.merge(user);

        this.entityManager.getTransaction().commit();
    }

    @Override
    public User findById(String id) {
        User user;

        this.entityManager.getTransaction().begin();

        user = this.entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();

        this.entityManager.getTransaction().commit();

        return user;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user;

        this.entityManager.getTransaction().begin();

        user = this.entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();

        this.entityManager.getTransaction().commit();

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users;

        this.entityManager.getTransaction().begin();

        users = this.entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();

        this.entityManager.getTransaction().commit();

        return users;
    }
}