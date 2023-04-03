package com.example.cinemaES.service;

import com.example.cinemaES.dto.HallDto;
import com.example.cinemaES.entity.CinemaHall;
import com.example.cinemaES.repository.CinemaHallRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class CinemaHallServiceTest {
    @Mock
    private CinemaHallRepository cinemaHallRepository;
    @InjectMocks
    private CinemaHallService cinemaHallService;

    @Test
    void getAll_ShouldReturnListOfHallDto() {
        // given
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setId("1");
        cinemaHall.setSeatsSet("10x10");
        List<CinemaHall> cinemaHallList = List.of(cinemaHall);
        when(cinemaHallRepository.findAll()).thenReturn(cinemaHallList);

        HallDto expectedHallDto = new HallDto();
        expectedHallDto.setId("1");
        expectedHallDto.setSeatSet("10x10");

        // when
        List<HallDto> result = cinemaHallService.getAll();

        // then
        assertEquals(List.of(expectedHallDto), result);
    }

}