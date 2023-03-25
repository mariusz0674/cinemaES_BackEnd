package com.example.cinemaES.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    @GetMapping()
    public ResponseEntity<ArrayList<String>> sayHello() {
        ArrayList<String> demo = new ArrayList<>();
        demo.add("esloo");
        return ResponseEntity.ok(demo);
    }

}