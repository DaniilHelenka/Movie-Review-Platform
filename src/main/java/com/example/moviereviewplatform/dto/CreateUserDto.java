package com.example.moviereviewplatform.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String name;
    String email;
    String password;
    String role;
}
