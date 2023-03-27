package com.example.cinemaES.entity;

import com.example.cinemaES.enums.AudioLanguage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "seance")
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seance_data")
    private Date seanceData;

    @Enumerated(EnumType.STRING)
    @Column(name = "audio_language")
    private AudioLanguage audioLanguage;

    private Boolean subtitle;


    @OneToOne(mappedBy = "seance", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private CinemaHallEvent cinemaHallEvent;

    @ManyToOne
    @JoinColumn(name = "movie_title")
    private Movie movie;

}
