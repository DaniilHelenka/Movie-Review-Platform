package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.dto.ReviewDto;
import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.entity.Reviews;
import com.example.moviereviewplatform.entity.User;
import com.example.moviereviewplatform.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDao implements Dao<Integer, Reviews> {
    private static final ReviewDao INSTANCE = new ReviewDao();
    public  static final String FIND_BY_ID = """
                          SELECT *
                          FROM reviews
                          WHERE movie_id = ?;                     
                                             """;
    public static final String INSERT_REVIEW_SQL = "INSERT INTO reviews (user_id, movie_id, rating, comment) VALUES (?, ?, ?, ?)";
    public static final String FIND_ALL_REVIEWS = "SELECT r.id, r.movie_id, r.rating, r.comment, r.created_at, u.id AS user_id " +
                                                   "FROM reviews r " +
                                                   "JOIN users u ON r.user_id = u.id";

    private ReviewDao() {
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
    public Reviews save(Reviews review) {

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(INSERT_REVIEW_SQL, Statement.RETURN_GENERATED_KEYS )){

            // Устанавливаем параметры в PreparedStatement
            preparedStatement.setInt(1, review.getUserId());    // user_id
            preparedStatement.setInt(2, review.getMovieId());   // movie_id
            preparedStatement.setInt(3, review.getRating());    // rating
            preparedStatement.setString(4, review.getComment()); // comment

            // Выполняем запрос
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        review.setId(generatedKeys.getInt(1)); // Устанавливаем ID для объекта
                    }
                }
            }
            return review;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при добавлении отзыва в базу данных", e);
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
                    resultSet.getObject("comment", String.class),
                    resultSet.getObject("created_at", LocalDateTime.class)
        );
    }
}
