package com.example.cinemaES.repository;

import com.example.cinemaES.entity.CinemaHallEvent;
import com.example.cinemaES.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeanceRepository extends JpaRepository<Seance, Integer> {

    @Query("SELECT s FROM Seance s WHERE s.cinemaHallEvent = :cinemaHallEvent")
    Optional<Seance> getSeanceByCinemaHallEvent(CinemaHallEvent cinemaHallEvent);

    @Query("SELECT s FROM Seance s")
    List<Seance> findAll();





}
