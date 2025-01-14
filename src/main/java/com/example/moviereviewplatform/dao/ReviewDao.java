package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.dto.ReviewDto;
import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.entity.Reviews;
import com.example.moviereviewplatform.entity.User;
import com.example.moviereviewplatform.util.ConnectionManager;
import com.example.moviereviewplatform.util.HibernateUtil;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ReviewDao extends AbstractHibernateDao<Integer, Reviews> {
    private static final ReviewDao INSTANCE = new ReviewDao(HibernateUtil.getSessionFactory());
    public  static final String FIND_BY_ID = """
                          SELECT *
                          FROM reviews
                          WHERE movie_id = ?;                     
                                             """;
    public static final String INSERT_REVIEW_SQL = "INSERT INTO reviews (user_id, movie_id, rating, comments, created_at) VALUES (? ,?, ?, ?, ?)";
    public static final String FIND_ALL_REVIEWS = "SELECT r.id, r.movie_id, r.rating, r.comments, r.created_at, u.id AS user_id " +
                                                   "FROM reviews r " +
                                                   "JOIN users u ON r.user_id = u.id";

    public ReviewDao(SessionFactory sessionFactory) {
        super(Reviews.class, sessionFactory);
    }


    public static ReviewDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Reviews> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_REVIEWS)) {
            var resultSet = preparedStatement.executeQuery();
            List<Reviews> reviews = new ArrayList<>();
            while (resultSet.next()){
                reviews.add(buildReview(resultSet));
            }
            return reviews;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Reviews> findById(Integer movieId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setObject(1, movieId);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) { // Если есть хотя бы одна строка
                    Reviews review = buildReview(resultSet);
                    return Optional.of(review); // Возвращаем найденный объект
                }
                return Optional.empty(); // Если данных нет
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Преобразуем SQL-исключение в Runtime
        }
    }



    @Override
    public boolean deleteById(Integer id) {
        return false;
    }


    private Reviews buildReview(ResultSet resultSet) throws SQLException {
        return new Reviews(
                    resultSet.getObject("id", Integer.class),
                    resultSet.getObject("movie_id", Integer.class),
                    resultSet.getObject("user_id", Integer.class),
                    resultSet.getObject("rating", Integer.class),
                    resultSet.getObject("comments", String.class),
                    resultSet.getObject("created_at", LocalDateTime.class)
        );
    }
}
