package com.example.moviereviewplatform.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Objects;

@Value
@Builder
public class ReviewDto {
    Integer id;
    Integer movieId;
    Integer rating;
    String comments;
    LocalDateTime created_at;
}
