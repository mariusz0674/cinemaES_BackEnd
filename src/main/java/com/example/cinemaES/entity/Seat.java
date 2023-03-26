package com.example.cinemaES.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seat_row")
    private Integer row;

    @Column(name = "seat_column")
    private Integer column;

    @Column(name = "is_taken")
    private Boolean isTaken;

    //@MapsId
    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_hall_event_id")
    private CinemaHallEvent cinemaHallEvent;
    // getters and setters
}
