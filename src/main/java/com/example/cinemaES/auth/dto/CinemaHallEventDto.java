package com.example.cinemaES.auth.dto;

import com.example.cinemaES.entity.Seance;
import com.example.cinemaES.entity.Seat;
import jakarta.persistence.*;
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
