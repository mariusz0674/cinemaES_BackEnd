package com.example.cinemaES.service;

import com.example.cinemaES.dto.HallDto;
import com.example.cinemaES.dto.Mapper;
import com.example.cinemaES.entity.CinemaHall;
import com.example.cinemaES.entity.CinemaHallEvent;
import com.example.cinemaES.repository.CinemaHallEventRepository;
import com.example.cinemaES.repository.CinemaHallRepository;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Loader;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CinemaHallService {
    private final CinemaHallRepository cinemaHallRepository;

    public List<HallDto> getAll(){
        List<HallDto> temp = cinemaHallRepository.findAll().stream().map(Mapper::hallToDto).toList();
        return temp;
    }
}
