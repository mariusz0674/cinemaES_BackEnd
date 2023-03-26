package com.example.cinemaES.demo;

import com.example.cinemaES.entity.Seance;
import com.example.cinemaES.repository.SeatRepository;
import com.example.cinemaES.service.SeanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    private final SeanceService seanceService;



    @GetMapping()
    public ResponseEntity<String> sayHello() {
        ArrayList<String> demo = new ArrayList<>();
        demo.add("esloo");
        seanceService.addDemo();
        return ResponseEntity.ok("elo");
    }



}