package com.example.cinemaES.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "cinema_hall")
public class CinemaHall {
    @Id
    private String id;
    @Column(name = "seats_set")
    private String seatsSet;
    @OneToMany()
    private List<CinemaHallEvent> cinemaHallEvent;
}
//
//      [][][][][][][][][[][]
//  [][][][][][][][][][][][][][]
//  [][][][][][][][][][][][][][]
//  [][][][][][][][][][][][][][]
//  [][][][][][][][][][][][][][]
//  [][][][][][][][][][][][][][]
//
//  [][][][][][][][][][][][][][]
//  [][][][][][][][][][][][][][]
//  [][][][][][][][][][][][][][]
//  [][][][][][][][][][][][][][]
//[][][][][][][][][][][][][][][][]
//
//
//
//
//
//
//
//
//
//
//