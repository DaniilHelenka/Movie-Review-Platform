package com.example.moviereviewplatform.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractHibernateDao<K, T> implements Dao<K, T> {

    private final Class<T> entityClass;
    private final SessionFactory sessionFactory;

    protected AbstractHibernateDao(Class<T> entityClass, SessionFactory sessionFactory) {
        this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
    public void save(T entity) {

        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка при сохранении объекта", e);
        }
    }
    public List<T> findAll() {
        try (Session session = getSession()) {
            return session.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при выполнении запроса findAll для " + entityClass.getName(), e);
        }
    }
    public Optional<T> findById(K id) {
        try (Session session = getSession()) {
            T entity = session.get(entityClass, (Serializable) id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске объекта по id: " + id, e);
        }
    }
    public boolean deleteById(K id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            T entity = session.get(entityClass, (Serializable) id);
            if (entity != null) {
                session.delete(entity);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка при удалении объекта с id: " + id, e);
        }
    }
}

