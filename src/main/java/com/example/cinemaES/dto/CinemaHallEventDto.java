package com.example.cinemaES.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CinemaHallEventDto {
    private Integer id;
    private List<SeatDto> seats;

}
