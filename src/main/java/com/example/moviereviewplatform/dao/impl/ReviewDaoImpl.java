package com.example.moviereviewplatform.dao.impl;

import com.example.moviereviewplatform.dao.AbstractHibernateDao;
import com.example.moviereviewplatform.dao.ReviewDao;
import com.example.moviereviewplatform.entity.Reviews;
import com.example.moviereviewplatform.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public class ReviewDaoImpl extends AbstractHibernateDao<Integer, Reviews> implements ReviewDao {

    private static final ReviewDaoImpl INSTANCE = new ReviewDaoImpl(HibernateUtil.getSessionFactory());

    private ReviewDaoImpl(SessionFactory sessionFactory) {
        super(Reviews.class, sessionFactory);
    }

    public static ReviewDaoImpl getInstance() {
        return INSTANCE;
    }

    public List<Reviews> findAllByMovieId(Integer movieId) {
        try (var session = getSession()) {
            String hql = "FROM Reviews WHERE movie.id = :movieId";
            return session.createQuery(hql, Reviews.class)
                    .setParameter("movieId", movieId)
                    .getResultList();
        }
    }
}
