package com.example.moviereviewplatform.service;

import com.example.moviereviewplatform.dao.UserDao;
import com.example.moviereviewplatform.dto.CreateUserDto;
import com.example.moviereviewplatform.validator.CreateUserValidator;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();

    public Long create(CreateUserDto userDto){
        return  null;
    }




    public static UserService getInstance(){
        return INSTANCE;
    }



}
