package com.example.cinemaES.auth.dto;

import com.example.cinemaES.entity.CinemaHallEvent;
import com.example.cinemaES.entity.Movie;
import com.example.cinemaES.entity.Seance;
import com.example.cinemaES.entity.Seat;
import com.example.cinemaES.repository.CinemaHallEventRepository;
import com.example.cinemaES.repository.SeanceRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Mapper {
    private final SeanceRepository seanceRepository;
    private final CinemaHallEventRepository cinemaHallEventRepository;

    public static SeanceDto seanceToDto(Seance seance){
        return SeanceDto.builder()
                .id(seance.getId())
                .cinemaHallEvent(cinemaHallEventDto(seance.getCinemaHallEvent()))
                .movie(movieToDto(seance.getMovie()))
                .date(seance.getSeanceData())
                .audioLanguage(seance.getAudioLanguage())
                .subtitle(seance.getSubtitle())
                .build();
    }

    public static MovieDto movieToDto(Movie movie){
        return MovieDto.builder()
                .title(movie.getTitle())
                .duration(movie.getDuration())
                .build();
    }

    public static CinemaHallEventDto cinemaHallEventDto(CinemaHallEvent cinemaHallEvent){
        return CinemaHallEventDto.builder()
                .id(cinemaHallEvent.getId())
                .seats(cinemaHallEvent.getSeats().stream().map(Mapper::SeatToDto).toList())
                .build();
    }

    public static SeatDto SeatToDto(Seat seat){
        return SeatDto.builder()
                .row(seat.getRow())
                .column(seat.getColumn())
                .isTaken(seat.getIsTaken())
                .build();
    }

    public static SeanceSimpleDto seanceToSimpleDto(Seance seance){
        return SeanceSimpleDto.builder()
                .id(seance.getId())
                .date(seance.getSeanceData())
                .movie_title(seance.getMovie().getTitle())
                .audioLanguage(seance.getAudioLanguage())
                .subtitle(seance.getSubtitle())
                .build();
    }



    public static Movie movieFromDto(MovieDto movieDto){
        return Movie.builder()
                .title(movieDto.getTitle())
                .duration(movieDto.getDuration())
                .build();
    }


}
