package com.example.moviereviewplatform.entity;

import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movies {

    private Integer id;

    private String name;

    private String genre;

    private String description;

    private String poster_url;

    private LocalDate release_date;

}
