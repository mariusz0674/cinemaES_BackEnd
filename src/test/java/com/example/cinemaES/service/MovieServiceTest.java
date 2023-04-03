package com.example.cinemaES.service;

import com.example.cinemaES.dto.Mapper;
import com.example.cinemaES.dto.MovieDto;
import com.example.cinemaES.entity.Movie;
import com.example.cinemaES.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieService movieService;

    @Test
    void saveMovie_ShouldSaveMovie() {
        // given
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Test movie");
        movieDto.setDuration(Time.valueOf("01:30:00"));

        // when
        boolean result = movieService.saveMovie(movieDto);

        // then
        verify(movieRepository, times(1)).save(Mapper.movieFromDto(movieDto));
        assertTrue(result);
    }

    @Test
    void getAllMovie_ShouldReturnListOfMovieDto() {
        // given
        Movie movie = new Movie();
        movie.setTitle("Test movie");
        movie.setDuration(Time.valueOf("01:30:00"));
        List<Movie> movieList = List.of(movie);
        when(movieRepository.findAll()).thenReturn(movieList);

        MovieDto expectedMovieDto = new MovieDto();
        expectedMovieDto.setTitle("Test movie");
        expectedMovieDto.setDuration(Time.valueOf("01:30:00"));

        // when
        List<MovieDto> result = movieService.getAllMovie();

        // then
        assertEquals(List.of(expectedMovieDto), result);
    }
}
