package com.example.cinemaES.controller;

import com.example.cinemaES.dto.CinemaHallEventDto;
import com.example.cinemaES.dto.SeanceDto;
import com.example.cinemaES.dto.SeanceSimpleDto;
import com.example.cinemaES.entity.CinemaHallEvent;
import com.example.cinemaES.entity.Seance;
import com.example.cinemaES.service.SeanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/seance")
public class SeanceController {
    private final SeanceService seanceService;

    @GetMapping
    public ResponseEntity<List<Seance>>getAllSeance(){
        return ResponseEntity.ok(seanceService.getAll());
    }

    @GetMapping(path = "/getSeance", params = "id")
    public ResponseEntity<SeanceDto>getSeanceById(@RequestParam("id") Integer id){
        return ResponseEntity.ok(seanceService.getById(id));
    }


    @GetMapping("/getSimpleAll")
    public ResponseEntity<List<SeanceSimpleDto>> getSimpleAll(){
        return ResponseEntity.ok(seanceService.getSimpleAll());
    }


    @PostMapping("/add")
    public ResponseEntity<Boolean>addSeance(@RequestBody SeanceSimpleDto seanceSimpleDto) throws ParseException {
        return ResponseEntity.ok(seanceService.addSeance(seanceSimpleDto));
    }

    @GetMapping(path = "/getHallEvent", params = "seanceId")
    public ResponseEntity<CinemaHallEventDto>getCinemaHallEvent(@RequestParam("seanceId") Integer seanceId){
        return ResponseEntity.ok(seanceService.getHallEventBySeanceId(seanceId));
    }

    @PostMapping("/updateSeats")
    public ResponseEntity<Boolean>updateSeats(@RequestBody CinemaHallEventDto cinemaHallEventDto){
        return ResponseEntity.ok(seanceService.updateCinemaHallEventDto(cinemaHallEventDto));
    }



}
