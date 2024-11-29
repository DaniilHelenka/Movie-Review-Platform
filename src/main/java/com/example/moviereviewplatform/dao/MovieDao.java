package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.service.MovieService;
import com.example.moviereviewplatform.util.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDao implements Dao<Long, Movies> {
    private static final MovieService movieService = new MovieService();
    private  static  final MovieDao INSTANCE = new MovieDao();
    private static final String FIND_ALL = """
            SELECT *
            FROM movies
            """;

    private MovieDao() {
    }

    public static MovieDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Movies> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Movies> movies = new ArrayList<>();
            while (resultSet.next()){
                movies.add(buildMovie(resultSet));
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movies> findById(Long id) {
        return null;
    }

    @Override
    public Movies save(Movies entity) {
        return null;
    }

    private Movies buildMovie(ResultSet resultSet) throws SQLException {
        return new Movies(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("description", String.class),
                resultSet.getObject("poster_url", String.class)

        );
    }

}
