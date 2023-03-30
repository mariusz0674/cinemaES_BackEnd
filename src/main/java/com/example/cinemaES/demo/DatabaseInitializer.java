package com.example.cinemaES.demo;

import com.example.cinemaES.entity.*;
import com.example.cinemaES.enums.AudioLanguage;
import com.example.cinemaES.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final CinemaHallRepository cinemaHallRepository;
    private final CinemaHallEventRepository cinemaHallEventRepository;

    private final MovieRepository movieRepository;
    private final SeatRepository seatRepository;
    private final SeanceRepository seanceRepository;


    @Override
    public void run(String... args) throws Exception {
        Movie movieA = Movie.builder()
                .title("Film o mrowkach")
                .duration(Time.valueOf("02:15:00"))
                .build();
        Movie movieB = Movie.builder()
                .title("Film o dwoch takich kaczkach")
                .duration(Time.valueOf("02:13:00"))
                .build();
        Movie movieC = Movie.builder()
                .title("Kaczor Donald")
                .duration(Time.valueOf("02:54:00"))
                .build();
        Movie movieD = Movie.builder()
                .title("Film o Ewelinie")
                .duration(Time.valueOf("03:15:00"))
                .build();
        Movie movieE = Movie.builder()
                .title("O 12 w Koninie")
                .duration(Time.valueOf("02:15:00"))
                .build();
        movieRepository.save(movieA);
        movieRepository.save(movieB);
        movieRepository.save(movieC);
        movieRepository.save(movieD);
        movieRepository.save(movieE);

        CinemaHall cinemaHallA = CinemaHall.builder()
                .id("A")
                .seatsSet(  "OOOXXXXXXXXXXOOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OOOOOOOOOOOOOOOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "XXXXXXXXXXXXXXXX"  )
                .build();
        CinemaHall cinemaHallB = CinemaHall.builder()
                .id("B")
                .seatsSet(  "OOOXXXXXXXXXXOOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OOOOOOOOOOOOOOOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "OXXXXXXXXXXXXXOO " +
                        "XXXXXXXXXXXXXXXX"  )
                .build();

        cinemaHallRepository.save(cinemaHallA);
        cinemaHallRepository.save(cinemaHallB);

        CinemaHallEvent cinemaHallEventA = CinemaHallEvent.builder()
                .cinemaHall(cinemaHallB)
                .build();
        setSeats(cinemaHallEventA, cinemaHallB);
        CinemaHallEvent cinemaHallEventB = CinemaHallEvent.builder()
                .cinemaHall(cinemaHallB)
                .build();
        setSeats(cinemaHallEventB, cinemaHallB);
        CinemaHallEvent cinemaHallEventC = CinemaHallEvent.builder()
                .cinemaHall(cinemaHallA)
                .build();
        setSeats(cinemaHallEventC, cinemaHallA);

        Seance seanceA = Seance.builder()
                .seanceData(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse("02.04.2023 15:45"))
                .audioLanguage(AudioLanguage.ENGLISH)
                .subtitle(false)
                .cinemaHallEvent(cinemaHallEventA)
                .movie(movieC)
                .build();

        cinemaHallEventA.setSeance(seanceA);
        //cinemaHallEventRepository.save(cinemaHallEventA);
        seanceRepository.save(seanceA);

        Seance seanceB = Seance.builder()
                .seanceData(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse("25.01.2023 15:45"))
                .audioLanguage(AudioLanguage.POLISH)
                .subtitle(false)
                .cinemaHallEvent(cinemaHallEventB)
                .movie(movieB)
                .build();

        cinemaHallEventB.setSeance(seanceB);
        //cinemaHallEventRepository.save(cinemaHallEventB);
        seanceRepository.save(seanceB);

        Seance seanceC = Seance.builder()
                .seanceData(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse("25.01.2023 15:45"))
                .audioLanguage(AudioLanguage.FRENCH)
                .subtitle(true)
                .cinemaHallEvent(cinemaHallEventC)
                .movie(movieA)
                .build();

        cinemaHallEventC.setSeance(seanceC);
        //cinemaHallEventRepository.save(cinemaHallEventC);
        seanceRepository.save(seanceC);




        //       createCinemaHalls();
//        createMovies();
//        createMovies();

    }
    private void createMovies(){
        Movie movieA = Movie.builder()
                .title("Film o mrowkach")
                .duration(Time.valueOf("02:15:00"))
                .build();
        Movie movieB = Movie.builder()
                .title("Film o dwoch takich kaczkach")
                .duration(Time.valueOf("02:13:00"))
                .build();
        Movie movieC = Movie.builder()
                .title("Kaczor Donald")
                .duration(Time.valueOf("02:54:00"))
                .build();
        Movie movieD = Movie.builder()
                .title("Film o Ewelinie")
                .duration(Time.valueOf("03:15:00"))
                .build();
        Movie movieE = Movie.builder()
                .title("O 12 w Koninie")
                .duration(Time.valueOf("02:15:00"))
                .build();
        movieRepository.save(movieA);
        movieRepository.save(movieB);
        movieRepository.save(movieC);
        movieRepository.save(movieD);
        movieRepository.save(movieE);
    }

    private void createCinemaHalls(){
        CinemaHall cinemaHallA = CinemaHall.builder()
                .id("A")
                .seatsSet(  "OOOXXXXXXXXXXOOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OOOOOOOOOOOOOOOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "XXXXXXXXXXXXXXXX"  )
                .build();
        CinemaHall cinemaHallB = CinemaHall.builder()
                .id("B")
                .seatsSet(  "OOOXXXXXXXXXXOOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OOOOOOOOOOOOOOOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "OXXXXXXXXXXXXXOO " +
                            "XXXXXXXXXXXXXXXX"  )
                .build();
        cinemaHallRepository.save(cinemaHallA);
        cinemaHallRepository.save(cinemaHallB);
    }
    private CinemaHallEvent setSeats(CinemaHallEvent cinemaHallEvent, CinemaHall cinemaHall) {
        String[] words = cinemaHall.getSeatsSet().split(" ");
        List<Seat> seatlist = new ArrayList<>();

        for (int k = 0; k < words.length; k++) {
            for (int i = 0; i < words[k].length(); i++) {
                if('X' == words[k].charAt(i)){
                    seatlist.add(Seat.builder()
                                    .row(k)
                                    .column(i)
                                    .cinemaHallEvent(cinemaHallEvent)
                                    .isTaken(((k+i)%3 == 0)? false : true)
                            .build());
                }
            }
        }
        cinemaHallEvent.setSeats(seatlist);
        return cinemaHallEvent;
    }

}
