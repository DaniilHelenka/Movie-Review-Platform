package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.service.MovieService;
import com.example.moviereviewplatform.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDao implements Dao<Long, Movies> {
    private static final MovieService movieService = new MovieService();
    private static final MovieDao INSTANCE = new MovieDao();
    public static final String FIND_ALL_MOVIES_WITH_POSTER = """
    SELECT m.id, m.name, m.genre, m.description, p.poster_url
    FROM movies m
    LEFT JOIN posters p ON m.id = p.movie_id;
""";
    private static final String FIND_ALL = """
            SELECT *
            FROM movies
            """;
    private static final String INSERT_MOVIE_SQL = """
        INSERT INTO movies (name, genre, description, poster, release_date) 
        VALUES (?, ?, ?, ?, ?)
        """;

    private MovieDao() {
    }

    public static MovieDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Movies> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_MOVIES_WITH_POSTER)) {
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
    public Movies save(Movies movie) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(INSERT_MOVIE_SQL, Statement.RETURN_GENERATED_KEYS )) {

            preparedStatement.setString(1, movie.getName());
            preparedStatement.setString(2, movie.getGenre());
            preparedStatement.setString(3, movie.getDescription());
            preparedStatement.setString(4, movie.getPoster_url());
            preparedStatement.setObject(5, movie.getRelease_date());

            int affectedRows = preparedStatement.executeUpdate();
       if (affectedRows > 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movie.setId(generatedKeys.getInt(1)); // Устанавливаем ID для объекта
                }
            }
        }
        return movie;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }



    private Movies buildMovie(ResultSet resultSet) throws SQLException {
        return new Movies(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("genre", String.class),
                resultSet.getObject("description", String.class),
                resultSet.getObject("poster_url", String.class)

        );
    }
}
