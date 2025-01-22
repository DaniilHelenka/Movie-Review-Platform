package com.example.moviereviewplatform.service.impl;

import com.example.moviereviewplatform.dao.impl.UserDaoImpl;
import com.example.moviereviewplatform.dto.CreateUserDto;
import com.example.moviereviewplatform.dto.UserDto;
import com.example.moviereviewplatform.exception.ValidationException;
import com.example.moviereviewplatform.mapper.CreateUserMapper;
import com.example.moviereviewplatform.mapper.UserMapper;
import com.example.moviereviewplatform.service.UserService;
import com.example.moviereviewplatform.validator.CreateUserValidator;
import lombok.SneakyThrows;


import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    private final CreateUserValidator createUserValidator;
    private final UserDaoImpl userDao;
    private final CreateUserMapper createUserMapper;
    private final ImageServiceImpl imageServiceImpl;
    private final UserMapper userMapper;

    private UserServiceImpl() {
        this.createUserValidator = CreateUserValidator.getInstance();
        this.userDao = UserDaoImpl.getInstance();
        this.createUserMapper = CreateUserMapper.getInstance();
        this.imageServiceImpl = ImageServiceImpl.getInstance();
        this.userMapper = UserMapper.getInstance();
    }

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }

    public Optional<UserDto> getUserById(Integer userId) {
        return userDao.findById(userId)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        imageServiceImpl.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.save(userEntity);
        return userEntity.getId();
    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }
}