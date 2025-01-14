package com.example.moviereviewplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Watchlist {
    @Id
    private int id;
    private int userId;
    private int movieId;
    private String listType;

}
