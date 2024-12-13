package com.example.moviereviewplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class WatchlistDto {
    Integer id;
    String movieName;
    String listType;
    Integer movieId;
}
