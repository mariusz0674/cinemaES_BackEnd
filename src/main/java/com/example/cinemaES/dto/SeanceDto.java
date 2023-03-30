package com.example.cinemaES.dto;

import com.example.cinemaES.enums.AudioLanguage;
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
