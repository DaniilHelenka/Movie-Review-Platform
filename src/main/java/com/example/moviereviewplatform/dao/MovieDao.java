package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public class MovieDao extends AbstractHibernateDao<Integer, Movies> {

    private static final MovieDao INSTANCE = new MovieDao(HibernateUtil.getSessionFactory());

    private MovieDao(SessionFactory sessionFactory) {
        super(Movies.class, sessionFactory);
    }

    public static MovieDao getInstance() {
        return INSTANCE;
    }

    public List<Movies> getTopRatedMovies() {
        try (var session = getSession()) {
            return session.createQuery("SELECT m FROM Movies m " +
                                       "JOIN m.reviews r " +
                                       "GROUP BY m.id, m.name, m.genre, m.description " +
                                       "ORDER BY AVG(r.rating) DESC, COUNT(r.id) DESC", Movies.class)
                    .setMaxResults(10)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении топ фильмов", e);
        }
    }
}