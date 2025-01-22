package com.example.moviereviewplatform.dao.impl;

import com.example.moviereviewplatform.dao.AbstractHibernateDao;
import com.example.moviereviewplatform.dao.WatchlistDao;
import com.example.moviereviewplatform.entity.Watchlist;
import com.example.moviereviewplatform.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public class WatchlistDaoImpl extends AbstractHibernateDao<Integer, Watchlist> implements WatchlistDao {

    private static final WatchlistDaoImpl INSTANCE = new WatchlistDaoImpl(HibernateUtil.getSessionFactory());

    public WatchlistDaoImpl(SessionFactory sessionFactory) {
        super(Watchlist.class, sessionFactory);
    }

    public static WatchlistDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Watchlist> findByUserId(int userId) {
        try (var session = getSession()) {
            String hql = "FROM Watchlist WHERE userId = :userId";
            return session.createQuery(hql, Watchlist.class)
                    .setParameter("userId", userId)
                    .getResultList();
        }
    }

    @Override
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
    @Override
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