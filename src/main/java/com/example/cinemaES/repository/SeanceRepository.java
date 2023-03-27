package com.example.cinemaES.repository;

import com.example.cinemaES.entity.CinemaHallEvent;
import com.example.cinemaES.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {
    Optional<Seance> getSeanceByCinemaHallEvent(CinemaHallEvent cinemaHallEvent);
    List<Seance> findAll();
}
