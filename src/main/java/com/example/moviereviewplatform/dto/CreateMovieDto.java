package com.example.moviereviewplatform.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
@Value
@Builder
public class CreateMovieDto {
    String name;
    String genre;
    String description;
    LocalDate release_date;
    Part poster_url;
}
