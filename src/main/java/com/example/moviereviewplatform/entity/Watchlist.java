package com.example.moviereviewplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Watchlist {
    private int id;
    private int userId;
    private int movieId;
    private String listType;

}
