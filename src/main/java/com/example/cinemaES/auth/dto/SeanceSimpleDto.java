package com.example.cinemaES.auth.dto;

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
public class SeanceSimpleDto {
    private Integer id;
    private Date date;
    private AudioLanguage audioLanguage;
    private Boolean subtitle;
   // private MovieDto movie;

    private String movie_title;

}
