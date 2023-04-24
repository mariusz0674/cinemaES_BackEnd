package com.example.cinemaES.repository;

import com.example.cinemaES.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String> {


    Optional<Movie> getMovieByTitle(String title);

    List<Movie> findAll();
}
