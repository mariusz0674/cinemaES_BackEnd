package com.example.cinemaES.controller;

import com.example.cinemaES.dto.HallDto;
import com.example.cinemaES.service.CinemaHallService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CinemaHallControllerTest {

    @Mock
    private CinemaHallService cinemaHallService;

    @InjectMocks
    private CinemaHallController cinemaHallController;

    @Test
    public void testGetAllId() {
        // Given
        List<HallDto> hallDtoList = Arrays.asList(new HallDto("A", "Hall A"), new HallDto("B", "Hall B"));
        when(cinemaHallService.getAll()).thenReturn(hallDtoList);

        // When
        ResponseEntity<List<HallDto>> response = cinemaHallController.getAllId();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hallDtoList, response.getBody());
    }
}
