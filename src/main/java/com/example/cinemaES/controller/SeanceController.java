package com.example.cinemaES.controller;

import com.example.cinemaES.auth.dto.SeanceDto;
import com.example.cinemaES.auth.dto.SeanceSimpleDto;
import com.example.cinemaES.entity.Seance;
import com.example.cinemaES.service.SeanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/seance")
public class SeanceController {
    private final SeanceService seanceService;

    @GetMapping
    public ResponseEntity<List<Seance>>getAllSeance(){
        return ResponseEntity.ok(seanceService.getAll());
    }

    @GetMapping(params = "id")
    public ResponseEntity<SeanceDto>getSeanceById(@RequestParam("id") Integer id){
        return ResponseEntity.ok(seanceService.getById(id));
    }

    @GetMapping("/getSimpleAll")
    public ResponseEntity<List<SeanceSimpleDto>> getSimpleAll(){
        return ResponseEntity.ok(seanceService.getSimpleAll());
    }


    @PostMapping("/add")
    public ResponseEntity<Boolean>addSeance(@RequestBody SeanceSimpleDto seanceSimpleDto){
        return ResponseEntity.ok(seanceService.addSeance(seanceSimpleDto));
    }

}
