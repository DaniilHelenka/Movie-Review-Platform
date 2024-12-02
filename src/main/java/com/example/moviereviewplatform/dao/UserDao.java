package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.User;
import com.example.moviereviewplatform.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao<Integer, User>{
    private  static  final UserDao INSTANCE = new UserDao();

    private final static String SAVE_SQL = "INSERT INTO users(name, email, password, role) values" +
            "(?, ?, ? ,?)";
    @Override
    @SneakyThrows
    public User save(User entity){
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getEmail());
            preparedStatement.setObject(3, entity.getPassword());
            preparedStatement.setObject(4, entity.getRole().name());

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
    public List<User> findAll() {
        return null;
    }
    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

}
