package com.example.cinemaES.service;

import com.example.cinemaES.auth.dto.Mapper;
import com.example.cinemaES.auth.dto.SeanceDto;
import com.example.cinemaES.auth.dto.SeanceSimpleDto;
import com.example.cinemaES.entity.CinemaHallEvent;
import com.example.cinemaES.entity.Movie;
import com.example.cinemaES.entity.Seance;
import com.example.cinemaES.entity.Seat;
import com.example.cinemaES.enums.AudioLanguage;
import com.example.cinemaES.repository.MovieRepository;
import com.example.cinemaES.repository.SeanceRepository;
import com.example.cinemaES.repository.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InstanceNotFoundException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class SeanceService {
    private final SeanceRepository seanceRepository;
    private final MovieRepository movieRepository;
   // private final CinemaHallEvent cinemaHallEvent;
    @SneakyThrows
    @Transactional
    public void addDemo() {
        Movie movie = Movie.builder()
                .title("Piraci z internetu")
                .duration(Time.valueOf("02:15:00"))
                .build();

        movieRepository.save(movie);

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

        Movie moviea = movieRepository.getMovieByTitle("Piraci z internetu").get();

        Seance seance = Seance.builder()
                .cinemaHallEvent(cinemaHallEvent)
                .movie(moviea)
                .subtitle(false)
                .audioLanguage(AudioLanguage.ENGLISH)
                .seanceData(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse("25.01.2023 15:45"))
                .build();

        seat1.setCinemaHallEvent(cinemaHallEvent);
        seat2.setCinemaHallEvent(cinemaHallEvent);
        cinemaHallEvent.setSeance(seance);
        seanceRepository.save(seance);

        return;// seanceRepository.getSeanceByCinemaHallEvent(cinemaHallEvent).get();
    }

    public List<Seance> getAll() {
        List<Seance> seance = seanceRepository.findAll();
        return List.of(
                Seance.builder()
                        .id(seance.get(0).getId())
                        .build());
    }

    public SeanceDto getById(Integer id) {

        Seance seance = seanceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found."));
        SeanceDto seanceDto = Mapper.seanceToDto(seance);

        return seanceDto;
    }

    public List<SeanceSimpleDto> getSimpleAll() {
        List<SeanceSimpleDto> seanceSimpleDtos = seanceRepository.findAll().stream().map(Mapper::seanceToSimpleDto).toList();

        return seanceSimpleDtos;
    }

    public Boolean addSeance(SeanceSimpleDto seanceSimpleDto) {
        CinemaHallEvent cinemaHallEvent = CinemaHallEvent.builder()
                .build();
        Seance seance = Seance.builder()
                .id(seanceSimpleDto.getId())
                .seanceData(seanceSimpleDto.getDate())
                .audioLanguage(seanceSimpleDto.getAudioLanguage())
                .subtitle(seanceSimpleDto.getSubtitle())
                .movie(movieRepository.findById(seanceSimpleDto.getMovie_title()).orElseThrow(() -> new EntityNotFoundException("No Title as: ")))
                .cinemaHallEvent(cinemaHallEvent)
                .build();


        cinemaHallEvent.setSeance(seance);
        seanceRepository.save(seance);
        return true;
    }
}
