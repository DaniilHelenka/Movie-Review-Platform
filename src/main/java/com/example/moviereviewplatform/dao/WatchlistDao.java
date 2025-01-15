package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class WatchlistDao extends AbstractHibernateDao<Integer, Watchlist> {

    private static final WatchlistDao INSTANCE = new WatchlistDao(HibernateUtil.getSessionFactory());

    public WatchlistDao(SessionFactory sessionFactory) {
        super(Watchlist.class, sessionFactory);
    }

    public static WatchlistDao getInstance() {
        return INSTANCE;
    }

    public List<Watchlist> findByUserId(int userId) {
        try (var session = getSession()) {
            String hql = "FROM Watchlist WHERE userId = :userId";
            return session.createQuery(hql, Watchlist.class)
                    .setParameter("userId", userId)
                    .getResultList();
        }
    }

    public Watchlist findByMovieIdAndListType(int movieId, String listType) {
        try (var session = getSession()) {
            String hql = "FROM Watchlist w WHERE w.movie.id = :movieId AND w.listType = :listType";
            return session.createQuery(hql, Watchlist.class)
                    .setParameter("movieId", movieId)
                    .setParameter("listType", listType)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }
    public void update(Watchlist watchlist) {
        try (var session = getSession()) {
            var transaction = session.beginTransaction();
            try {
                session.createQuery("UPDATE Watchlist SET listType = :listType WHERE id = :id")
                        .setParameter("listType", watchlist.getListType())
                        .setParameter("id", watchlist.getId())
                        .executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}