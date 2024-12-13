package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.Movies;
import com.example.moviereviewplatform.entity.Role;
import com.example.moviereviewplatform.entity.User;
import com.example.moviereviewplatform.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Integer, User> {
    private static final UserDao INSTANCE = new UserDao();

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL =
            "SELECT * FROM users WHERE email = ? AND password = ?";

    private static final String GET_BY_ID_SQL =
            "SELECT * FROM users WHERE id = ?";

    private final static String SAVE_SQL =
            "INSERT INTO users(name, email, password, role, image) values" +
            "(?, ?, ? ,?, ?)";

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getEmail());
            preparedStatement.setObject(3, entity.getPassword());
            preparedStatement.setObject(4, entity.getRole().name());
            preparedStatement.setObject(5, entity.getImage());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildEntity(resultSet);
            }

            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildEntity(resultSet);
            }

            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Movies> getTopRatedMovies() {
        return null;
    }

    private User buildEntity(ResultSet resultSet) throws java.sql.SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .email(resultSet.getObject("email", String.class))
                .image(resultSet.getObject("image", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .build();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

}
