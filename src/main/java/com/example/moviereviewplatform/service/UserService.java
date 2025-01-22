package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dto.CreateUserDto;
import com.example.moviereviewplatform.dto.UserDto;
import com.example.moviereviewplatform.exception.ValidationException;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> login(String email, String password);

    Optional<UserDto> getUserById(Integer userId);

    Integer create(CreateUserDto userDto) throws ValidationException;
}
