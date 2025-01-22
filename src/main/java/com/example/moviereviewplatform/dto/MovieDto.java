package com.example.moviereviewplatform.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;


@Value
@Builder
public class MovieDto {
    Integer id;
    String name;
    String genre;
    String description;
    String poster_url;
    LocalDate release_date;
}
