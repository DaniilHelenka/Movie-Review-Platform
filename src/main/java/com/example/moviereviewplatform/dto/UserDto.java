package com.example.moviereviewplatform.dto;

import com.example.moviereviewplatform.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    String email;
    String image;
    Role role;
}
