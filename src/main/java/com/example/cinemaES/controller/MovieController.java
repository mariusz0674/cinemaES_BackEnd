package com.example.cinemaES.controller;


import com.example.cinemaES.dto.MovieDto;
import com.example.cinemaES.service.MovieService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/getAll")
    public ResponseEntity<List<MovieDto>>getAllMovie(){
        return ResponseEntity.ok(movieService.getAllMovie());
    }

    @RolesAllowed(value = {"ADMIN", "ADMINISTRATION"})
    @PostMapping("/add")
    public ResponseEntity<Boolean> addMovie(@Valid @RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.saveMovie(movieDto));
    }

}
