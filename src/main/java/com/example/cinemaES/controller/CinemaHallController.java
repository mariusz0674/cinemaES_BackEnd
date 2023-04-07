package com.example.cinemaES.controller;


import com.example.cinemaES.dto.HallDto;
import com.example.cinemaES.service.CinemaHallService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hall")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    @RolesAllowed(value = {"ADMIN", "ADMINISTRATION"})
    @GetMapping("/getAll")
    public ResponseEntity<List<HallDto>>getAllId(){
        return ResponseEntity.ok(cinemaHallService.getAll());
    }


}
