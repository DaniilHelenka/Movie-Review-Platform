package com.example.moviereviewplatform.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WatchlistDto {
    int id;
    String movieName;
    String listType;
}
