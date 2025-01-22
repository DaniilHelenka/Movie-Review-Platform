package com.example.moviereviewplatform.dao;

import com.example.moviereviewplatform.entity.User;
import java.util.Optional;

public interface UserDao extends Dao<Integer, User> {
    Optional<User> findByEmailAndPassword(String email, String password);
}
