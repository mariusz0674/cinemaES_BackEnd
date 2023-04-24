package com.example.cinemaES.dto;

import com.example.cinemaES.enums.AudioLanguage;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Seance id is mandatory")
    private Integer id;
    @NotNull(message = "Seance date is mandatory")
    @Future(message = "Seance date must be in the future")
    private Date date;
    @NotNull(message = "Seance audioLanguage is mandatory")
    private AudioLanguage audioLanguage;
    @NotNull(message = "Seance subtitle is mandatory")
    private Boolean subtitle;
    @NotBlank(message = "Seance movie_title is mandatory")
    private String hall_id;
    @NotBlank(message = "Seance movie_title is mandatory")
    private String movie_title;

}
