package com.example.cinemaES.controller;


import com.example.cinemaES.dto.HallDto;
import com.example.cinemaES.entity.CinemaHall;
import com.example.cinemaES.service.CinemaHallService;
import com.example.cinemaES.service.MovieService;
import lombok.AllArgsConstructor;
import org.hibernate.engine.spi.Resolution;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/hall")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;

    @GetMapping("/getAll")
    public ResponseEntity<List<HallDto>>getAllId(){
        return ResponseEntity.ok(cinemaHallService.getAll());
    }


}
