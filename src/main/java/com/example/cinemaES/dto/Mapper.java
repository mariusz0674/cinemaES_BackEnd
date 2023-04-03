package com.example.cinemaES.dto;

import com.example.cinemaES.entity.*;
import com.example.cinemaES.repository.CinemaHallEventRepository;
import com.example.cinemaES.repository.SeanceRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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


    public static User userToDto(UserDto user){
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
//    public static CinemaHallEvent cinemaHallEventFromDto(CinemaHallEventDto cinemaHallEventDto, Seance){
//        CinemaHallEvent cinemaHallEvent = new CinemaHallEvent();
//        List<Seat> seats = new ArrayList<>();
//        cinemaHallEventDto.getSeats().forEach( seatDto -> {
//            seats.add(Mapper.seatFromDto(seatDto, cinemaHallEvent));
//        });
//
//
//        cinemaHallEvent.setSeats(seats);
//        cinemaHallEvent.setId(cinemaHallEventDto.getId());
//        //cinemaHallEvent.setSeance(seanceRepository.getSeanceByCinemaHallEvent(cinemaHallEvent));
//
//        return cinemaHallEvent;
//    }


    public static SeatDto SeatToDto(Seat seat){
        return SeatDto.builder()
                .row(seat.getRow())
                .column(seat.getColumn())
                .isTaken(seat.getIsTaken())
                .build();
    }

    public static Seat seatFromDto(SeatDto seatDto, CinemaHallEvent cinemaHallEvent){
        return Seat.builder()
                .column(seatDto.getColumn())
                .row(seatDto.getRow())
                .isTaken(seatDto.getIsTaken())
                .cinemaHallEvent(cinemaHallEvent)
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

    public static HallDto hallToDto(CinemaHall cinemaHall){
        return HallDto.builder()
                .id(cinemaHall.getId())
                .seatSet(cinemaHall.getSeatsSet())
                .build();
    }


}
