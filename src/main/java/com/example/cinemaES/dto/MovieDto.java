package com.example.cinemaES.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Movie title is mandatory")
    private String title;
    @NotNull(message = "Movie duration is mandatory")
    private Time duration;

}
