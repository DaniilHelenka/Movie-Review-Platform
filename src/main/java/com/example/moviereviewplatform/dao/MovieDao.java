package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.service.MovieService;
import com.example.moviereviewplatform.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDao implements Dao<Integer, Movies> {
    private static final MovieService movieService = new MovieService();
    private static final MovieDao INSTANCE = new MovieDao();

    private static final String GET_TOP_RATED_MOVIES_QUERY = """
            SELECT 
                m.id, 
                m.name, 
                m.genre, 
                m.description, 
                AVG(r.rating) AS average_rating, 
                COUNT(r.id) AS review_count
            FROM movies m
            JOIN reviews r ON m.id = r.movie_id
            GROUP BY m.id, m.name, m.genre, m.description
            ORDER BY average_rating DESC, review_count DESC
            LIMIT 10;
            """;
    public static final String FIND_ALL_MOVIES_WITH_POSTER = """
                SELECT m.id, m.name, m.genre, m.description, p.poster_url, m.release_date
                FROM movies m
                LEFT JOIN posters p ON m.id = p.movie_id;
            """;
    private static final String INSERT_MOVIE_SQL = """
            INSERT INTO movies (name, genre, description, release_date, poster_url) 
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String DELETE_MOVIE_SQL =
            "DELETE FROM movies WHERE id = ?";



    private MovieDao() {
    }

    public static MovieDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Movies> findAll() {
        String query = "SELECT id, name, genre, description, poster_url, release_date FROM movies";
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(query)) {
            var resultSet = preparedStatement.executeQuery();
            List<Movies> movies = new ArrayList<>();
            while (resultSet.next()) {
                movies.add(buildMovie(resultSet)); // Метод buildMovie заполняет объект Movies из ResultSet
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при выполнении запроса findAll: " + e.getMessage(), e);
        }
    }
    @Override
    public Optional<Movies> findById(Integer id) {
        String sql = "SELECT * FROM movies WHERE id = ?";
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(buildMovie(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    @Override
    public List<Movies> getTopRatedMovies() {
        List<Movies> topMovies = new ArrayList<>();
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_TOP_RATED_MOVIES_QUERY);
             var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Movies movies = Movies.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .genre(resultSet.getString("genre"))
                        .description(resultSet.getString("description"))
                        .build();
                topMovies.add(movies);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topMovies;
    }

    @Override
    public Movies save(Movies movie) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(INSERT_MOVIE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, movie.getName());
            preparedStatement.setString(2, movie.getGenre());
            preparedStatement.setString(3, movie.getDescription());
            preparedStatement.setObject(4, movie.getRelease_date());
            preparedStatement.setObject(5, movie.getPoster_url());

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

    @Override
    public boolean deleteById(Integer id) {
            try (var connection = ConnectionManager.get();
                 var preparedStatement = connection.prepareStatement(DELETE_MOVIE_SQL)) {

                preparedStatement.setLong(1, id);
                int affectedRows = preparedStatement.executeUpdate();
                return affectedRows > 0; // Возвращает true, если была удалена хотя бы одна строка
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при удалении фильма с id: " + id, e);
            }
        }


    private Movies buildMovie (ResultSet resultSet) throws SQLException {
            return new Movies(
                    resultSet.getObject("id", Integer.class),
                    resultSet.getObject("name", String.class),
                    resultSet.getObject("genre", String.class),
                    resultSet.getObject("description", String.class),
                    resultSet.getObject("poster_url", String.class),
                    resultSet.getObject("release_date", LocalDate.class)

            );
        }
    }
