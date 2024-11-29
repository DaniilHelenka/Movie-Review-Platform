package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.entity.Reviews;
import com.example.moviereviewplatform.entity.User;
import com.example.moviereviewplatform.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    private ReviewDao() {
    }

    public static ReviewDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Reviews> findAll() {
        return null;
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
