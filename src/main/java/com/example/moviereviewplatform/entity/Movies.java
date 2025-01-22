package com.example.moviereviewplatform.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String genre;

    private String description;

    private String poster_url;

    private LocalDate release_date;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)  // Связь с Watchlist
    private List<Watchlist> watchlist;  // Список всех записей в Watchlist для этого фильма

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Reviews> reviews;
}