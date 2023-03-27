package com.example.cinemaES.auth.dto;

import com.example.cinemaES.entity.CinemaHallEvent;
import com.example.cinemaES.entity.Movie;
import com.example.cinemaES.enums.AudioLanguage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeanceDto {

    private Integer id;
    private Date date;
    private CinemaHallEventDto cinemaHallEvent;

    private AudioLanguage audioLanguage;
    private Boolean subtitle;
    private MovieDto movie;
}
