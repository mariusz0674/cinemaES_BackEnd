package com.example.cinemaES.service;

import com.example.cinemaES.dto.SeanceDto;
import com.example.cinemaES.entity.CinemaHallEvent;
import com.example.cinemaES.entity.Movie;
import com.example.cinemaES.entity.Seance;
import com.example.cinemaES.entity.Seat;
import com.example.cinemaES.enums.AudioLanguage;
import com.example.cinemaES.repository.CinemaHallEventRepository;
import com.example.cinemaES.repository.CinemaHallRepository;
import com.example.cinemaES.repository.MovieRepository;
import com.example.cinemaES.repository.SeanceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class SeanceServiceTest {
    @Mock
    private SeanceRepository seanceRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CinemaHallRepository cinemaHallRepository;
    @Mock
    private CinemaHallEventRepository cinemaHallEventRepository;
    @Mock
    private CinemaHallService cinemaHallEventService;
    @InjectMocks
    private SeanceService seanceService;
    private Movie movie;
    private Seat seat1;
    private Seat seat2;
    private CinemaHallEvent cinemaHallEvent;
    private Seance seance;

    @BeforeEach
    public void setup() throws Exception {
        movie = Movie.builder()
                .title("Piraci z internetu")
                .duration(Time.valueOf("02:15:00"))
                .build();

        seat1 = Seat.builder()
                .row(5)
                .column(6)
                .isTaken(false)
                .build();

        seat2 = Seat.builder()
                .row(5)
                .column(7)
                .isTaken(true)
                .build();

        cinemaHallEvent = CinemaHallEvent.builder()
                .seats(List.of(seat1, seat2))
                .build();

        seance = Seance.builder()
                .cinemaHallEvent(cinemaHallEvent)
                .movie(movie)
                .subtitle(false)
                .audioLanguage(AudioLanguage.ENGLISH)
                .seanceData(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse("25.01.2023 15:45"))
                .build();

        seat1.setCinemaHallEvent(cinemaHallEvent);
        seat2.setCinemaHallEvent(cinemaHallEvent);
        cinemaHallEvent.setSeance(seance);
    }


    @Test
    public void testGetAll() {
        when(seanceRepository.findAll()).thenReturn(List.of(seance));

        List<Seance> result = seanceService.getAll();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(seance.getId(), result.get(0).getId());
    }

    @Test
    public void testGetById() {
        when(seanceRepository.findById(seance.getId())).thenReturn(Optional.of(seance));

        SeanceDto result = seanceService.getById(seance.getId());

        Assertions.assertEquals(seance.getId(), result.getId());
    }
    @Test
    public void testGetById_shouldThrowExceptionWhenNotFound() {
        Long nonExistentId = 123L;
        when(seanceRepository.findById(Math.toIntExact(nonExistentId))).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            seanceService.getById(Math.toIntExact(nonExistentId));
        });
    }

}


