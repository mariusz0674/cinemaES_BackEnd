package com.example.cinemaES.entity;

import com.example.cinemaES.enums.AudioLanguage;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Builder
@Table(name = "movie")
public class Movie {

    @Column(name = "title")
    @Id
    private String title;


    private Time duration;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Seance> seances;



}
