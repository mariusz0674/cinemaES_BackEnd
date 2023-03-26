package com.example.cinemaES.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne(optional = false)
    @JoinColumn(name = "seance_id", referencedColumnName = "id")
    private Seance seance;
}