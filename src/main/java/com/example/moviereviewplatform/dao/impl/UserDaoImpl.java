package com.example.moviereviewplatform.dao.impl;

import com.example.moviereviewplatform.dao.AbstractHibernateDao;
import com.example.moviereviewplatform.dao.UserDao;
import com.example.moviereviewplatform.entity.User;
import com.example.moviereviewplatform.util.HibernateUtil;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;

import java.util.Optional;


public class UserDaoImpl extends AbstractHibernateDao<Integer, User> implements UserDao {
    private static final UserDaoImpl INSTANCE = new UserDaoImpl(HibernateUtil.getSessionFactory());

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var session = getSession()) {
            String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
            var query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.uniqueResultOptional();
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (var session = getSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (var session = getSession()) {
            var transaction = session.beginTransaction();
            var user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                return true;
            }
            return false;
        }
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }
}