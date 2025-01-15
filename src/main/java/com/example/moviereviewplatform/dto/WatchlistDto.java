package com.example.moviereviewplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
public class WatchlistDto {
    Integer id;
    String movieName;
    String listType;
    Integer movieId;
}
