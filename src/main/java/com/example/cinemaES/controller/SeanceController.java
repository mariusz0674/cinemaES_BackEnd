package com.example.cinemaES.controller;
import com.example.cinemaES.dto.CinemaHallEventDto;
import com.example.cinemaES.dto.SeanceDto;
import com.example.cinemaES.dto.SeanceSimpleDto;
import com.example.cinemaES.entity.Seance;
import com.example.cinemaES.service.SeanceService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/seance")
public class SeanceController {
    private final SeanceService seanceService;


    @RolesAllowed(value = {"ADMIN", "CASHIER", "ATTENDANT", "ADMINISTRATION", "DEFAULT"})
    @GetMapping
    public ResponseEntity<List<Seance>>getAllSeance(){
        return ResponseEntity.ok(seanceService.getAll());
    }
    @RolesAllowed(value = {"ADMIN", "CASHIER", "ATTENDANT", "ADMINISTRATION", "DEFAULT"})
    @GetMapping(path = "/getSeance", params = "id")
    public ResponseEntity<SeanceDto>getSeanceById(@RequestParam("id") Integer id){
        return ResponseEntity.ok(seanceService.getById(id));
    }

    @GetMapping("/getSimpleAll")
    public ResponseEntity<List<SeanceSimpleDto>> getSimpleAll(){
        return ResponseEntity.ok(seanceService.getSimpleAll());
    }
    @RolesAllowed(value = {"ADMIN", "ADMINISTRATION"})
    @PostMapping("/add")
    public ResponseEntity<Boolean>addSeance(@Valid @RequestBody SeanceSimpleDto seanceSimpleDto) {
        return ResponseEntity.ok(seanceService.addSeance(seanceSimpleDto));
    }

    @RolesAllowed(value = {"ADMIN", "CASHIER", "ATTENDANT", "ADMINISTRATION", "DEFAULT"})
    @GetMapping(path = "/getHallEvent", params = "seanceId")
    public ResponseEntity<CinemaHallEventDto>getCinemaHallEvent(@RequestParam("seanceId") Integer seanceId){
        return ResponseEntity.ok(seanceService.getHallEventBySeanceId(seanceId));
    }
    @RolesAllowed(value = {"ADMIN", "CASHIER", "ATTENDANT", "ADMINISTRATION", "DEFAULT"})

    @PostMapping("/updateSeats")
    public ResponseEntity<Boolean>updateSeats(@RequestBody CinemaHallEventDto cinemaHallEventDto){
        return ResponseEntity.ok(seanceService.updateCinemaHallEventDto(cinemaHallEventDto));
    }



}
