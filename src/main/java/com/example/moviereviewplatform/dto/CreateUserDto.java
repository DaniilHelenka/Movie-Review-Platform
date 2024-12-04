package com.example.moviereviewplatform.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String name;
    String email;
    Part image;
    String password;
    String role;
}
