package com.example.cinemaES.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class HallDto {
    private String id;
    private String seatSet;
}
