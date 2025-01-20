package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.dto.MovieDto;
import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MovieDaoImpl extends AbstractHibernateDao<Integer, Movies> implements MovieDao{

    private static final MovieDaoImpl INSTANCE = new MovieDaoImpl(HibernateUtil.getSessionFactory());

    private MovieDaoImpl(SessionFactory sessionFactory) {
        super(Movies.class, sessionFactory);
    }

    public static MovieDaoImpl getInstance() {
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
    public List<MovieDto> findAllPaginated(int page, int size) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movies> query = session.createQuery("FROM Movies", Movies.class);
            query.setFirstResult((page - 1) * size); // Устанавливаем смещение
            query.setMaxResults(size); // Устанавливаем лимит

            // Преобразование списка сущностей Movies в DTO
            return query.list().stream()
                    .map(movie -> new MovieDto(
                            movie.getId(),
                            movie.getName(),
                            movie.getGenre(),
                            movie.getDescription(),
                            movie.getPoster_url(),
                            movie.getRelease_date()
                    ))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении фильмов с пагинацией", e);
        }
    }
}