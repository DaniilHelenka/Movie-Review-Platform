package com.example.moviereviewplatform.mapper;

import com.example.moviereviewplatform.dto.CreateUserDto;
import com.example.moviereviewplatform.entity.Role;
import com.example.moviereviewplatform.entity.User;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class CreateUserMapper implements Mapper<CreateUserDto, User>{

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .build();
    }
    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
