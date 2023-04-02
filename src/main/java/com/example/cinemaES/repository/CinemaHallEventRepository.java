package com.example.cinemaES.repository;

import com.example.cinemaES.entity.CinemaHallEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaHallEventRepository extends JpaRepository<CinemaHallEvent, Integer> {
       // public CinemaHallEvent findById
}

