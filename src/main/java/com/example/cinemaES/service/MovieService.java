package com.example.cinemaES.service;

import com.example.cinemaES.dto.Mapper;
import com.example.cinemaES.dto.MovieDto;
import com.example.cinemaES.repository.MovieRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Boolean saveMovie(MovieDto movieDto){
        if(!movieRepository.existsById(movieDto.getTitle())){
            movieRepository.save(Mapper.movieFromDto(movieDto));
            return true;
        }
        throw new EntityExistsException("Entity with movieTitle " + movieDto.getTitle() + " already exists.");
    }

    public List<MovieDto> getAllMovie() {
        return movieRepository.findAll().stream().map(Mapper::movieToDto).toList();
    }
}
