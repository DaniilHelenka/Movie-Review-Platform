package com.example.moviereviewplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
@AllArgsConstructor
@Value
@Builder
public class CreateUserDto {
    String name;
    String email;
    String password;
    String role_id;
}
