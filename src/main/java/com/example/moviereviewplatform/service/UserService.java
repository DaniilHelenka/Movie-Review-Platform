package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.UserDao;
import com.example.moviereviewplatform.dto.CreateUserDto;
import com.example.moviereviewplatform.dto.UserDto;
import com.example.moviereviewplatform.entity.User;
import com.example.moviereviewplatform.exception.ValidationException;
import com.example.moviereviewplatform.mapper.CreateUserMapper;
import com.example.moviereviewplatform.mapper.UserMapper;
import com.example.moviereviewplatform.validator.CreateUserValidator;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;


import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }
    public Optional<User> getUserById(Integer user_id) {
        return userDao.findById(user_id);
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto){
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.save(userEntity);
        return userEntity.getId();
    }


    public static UserService getInstance() {
        return INSTANCE;
    }


}
