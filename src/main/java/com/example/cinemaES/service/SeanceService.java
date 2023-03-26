package com.example.cinemaES.service;

import com.example.cinemaES.entity.CinemaHallEvent;
import com.example.cinemaES.entity.Seance;
import com.example.cinemaES.entity.Seat;
import com.example.cinemaES.repository.SeanceRepository;
import com.example.cinemaES.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeanceService {
    private final SeanceRepository seanceRepository;
   // private final CinemaHallEvent cinemaHallEvent;
    @Transactional
    public void addDemo() {
        Seat seat1 = Seat.builder()
                .row(5)
                .column(6)
                .isTaken(false)
                .build();
        Seat seat2 = Seat.builder()
                .row(5)
                .column(7)
                .isTaken(true)
                .build();
        CinemaHallEvent cinemaHallEvent = CinemaHallEvent.builder()
                .seats(List.of(seat1,seat2))
                .build();
        Seance seance = Seance.builder()
                .cinemaHallEvent(cinemaHallEvent)
                .build();

        seat1.setCinemaHallEvent(cinemaHallEvent);
        seat2.setCinemaHallEvent(cinemaHallEvent);
        cinemaHallEvent.setSeance(seance);
        seanceRepository.save(seance);

        return;// seanceRepository.getSeanceByCinemaHallEvent(cinemaHallEvent).get();
    }
}
