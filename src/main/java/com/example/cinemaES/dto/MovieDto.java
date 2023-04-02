package com.example.cinemaES.dto;

import com.example.cinemaES.enums.AudioLanguage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private String title;
    private Time duration;

}
