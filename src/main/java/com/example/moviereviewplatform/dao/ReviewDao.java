package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Reviews;
import com.example.moviereviewplatform.util.HibernateUtil;
import org.hibernate.SessionFactory;
import java.util.List;

public class ReviewDao extends AbstractHibernateDao<Integer, Reviews> {

    private static final ReviewDao INSTANCE = new ReviewDao(HibernateUtil.getSessionFactory());

    private ReviewDao(SessionFactory sessionFactory) {
        super(Reviews.class, sessionFactory);
    }

    public static ReviewDao getInstance() {
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
