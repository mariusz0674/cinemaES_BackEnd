package com.example.cinemaES.service;

import com.example.cinemaES.dto.CinemaHallEventDto;
import com.example.cinemaES.dto.Mapper;
import com.example.cinemaES.dto.SeanceDto;
import com.example.cinemaES.dto.SeanceSimpleDto;
import com.example.cinemaES.entity.CinemaHallEvent;
import com.example.cinemaES.entity.Seance;
import com.example.cinemaES.entity.Seat;
import com.example.cinemaES.repository.*;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.*;

@Service
@AllArgsConstructor
public class SeanceService {
    private final SeanceRepository seanceRepository;
    private final MovieRepository movieRepository;
    private final CinemaHallRepository cinemaHallRepository;
    private final CinemaHallEventRepository cinemaHallEventRepository;
   // private final CinemaHallEvent cinemaHallEvent;


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

    private boolean checkIfSeanceExistsInCinemaHall(Date x, Date y, String z) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Seance> criteriaQuery = builder.createQuery(Seance.class);
            Root<Seance> root = criteriaQuery.from(Seance.class);
            Join<Seance, CinemaHallEvent> cinemaHallEventJoin = root.join("cinemaHallEvent");
            criteriaQuery.select(root);
            criteriaQuery.where(builder.between(root.get("seanceData"), x, y),
                    builder.equal(cinemaHallEventJoin.get("cinemaHall").get("id"), z));
            List<Seance> seances = session.createQuery(criteriaQuery).getResultList();
            return !seances.isEmpty();
        }
    }
    private void checkHallAvailable(SeanceSimpleDto seanceSimpleDto) {
        if(checkIfSeanceExistsInCinemaHall(seanceSimpleDto.getDate(), Date.from(seanceSimpleDto.getDate().toInstant().plusSeconds(360000)), seanceSimpleDto.getHall_id())){
            throw new EntityExistsException("Hall is not available at this time");
        }
    }
    public Boolean addSeance(SeanceSimpleDto seanceSimpleDto) throws ParseException {
        checkHallAvailable(seanceSimpleDto);
        CinemaHallEvent cinemaHallEvent = CinemaHallEvent.builder()
                .cinemaHall(cinemaHallRepository.findById(seanceSimpleDto.getHall_id()).get())
                .build();
        Seance seance = Seance.builder()
               // .id(seanceSimpleDto.getId())
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


    public CinemaHallEventDto getHallEventBySeanceId(Integer seanceId) {
        Seance seance = seanceRepository.findById(seanceId).orElseThrow(() -> new EntityNotFoundException("No such seance"));
        CinemaHallEvent cinemaHallEvent = seance.getCinemaHallEvent();
        return Mapper.cinemaHallEventDto(cinemaHallEvent);
    }

    public Boolean updateCinemaHallEventDto(CinemaHallEventDto cinemaHallEventDto) {
        CinemaHallEvent cinemaHallEvent = new CinemaHallEvent();
        cinemaHallEvent.setId(cinemaHallEventDto.getId());

        List<Seat> seats = new ArrayList<>();
        cinemaHallEventDto.getSeats().forEach( seatDto -> {
            seats.add(Mapper.seatFromDto(seatDto, cinemaHallEvent));
        });


        cinemaHallEvent.setSeats(seats);
        cinemaHallEvent.setSeance(seanceRepository.getSeanceByCinemaHallEvent(cinemaHallEvent).get());

        cinemaHallEventRepository.save(cinemaHallEvent);
        return true;
    }


}
