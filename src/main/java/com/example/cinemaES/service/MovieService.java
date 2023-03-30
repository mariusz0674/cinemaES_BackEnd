package com.example.cinemaES.service;

import com.example.cinemaES.dto.Mapper;
import com.example.cinemaES.dto.MovieDto;
import com.example.cinemaES.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Boolean saveMovie(MovieDto movieDto){
        movieRepository.save(Mapper.movieFromDto(movieDto));
        return true;
    }

    public List<MovieDto> getAllMovie() {
        return movieRepository.findAll().stream().map(Mapper::movieToDto).toList();
    }
}
