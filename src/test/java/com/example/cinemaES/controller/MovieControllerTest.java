package com.example.cinemaES.controller;

import com.example.cinemaES.dto.MovieDto;
import com.example.cinemaES.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @Test
    public void testGetAllMovie() {
        // Given
        List<MovieDto> movieDtoList = Arrays.asList(new MovieDto("Title one", Time.valueOf("03:30:15")), new MovieDto("Title two", Time.valueOf("04:30:15")));
        when(movieService.getAllMovie()).thenReturn(movieDtoList);

        // When
        ResponseEntity<List<MovieDto>> response = movieController.getAllMovie();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movieDtoList, response.getBody());
    }

    @Test
    public void testAddMovie() {
        // Given
        MovieDto movieDto = new MovieDto("Title one",  Time.valueOf("03:30:15"));

        when(movieService.saveMovie(movieDto)).thenReturn(true);

        // When
        ResponseEntity<Boolean> response = movieController.addMovie(movieDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }
}
