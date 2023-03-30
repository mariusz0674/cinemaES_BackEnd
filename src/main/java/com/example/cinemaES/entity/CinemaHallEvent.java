package com.example.cinemaES.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor

@Data
@Builder
@Entity
@Table(name = "cinema_hall_event")
public class CinemaHallEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "cinemaHallEvent", cascade = CascadeType.ALL)
    private List<Seat> seats;

    @ManyToOne()
    @JoinColumn(name = "cinema_hall_id")
    private CinemaHall cinemaHall;

    @OneToOne(optional = false)
    @JoinColumn(name = "seance_id", referencedColumnName = "id")
    private Seance seance;


}