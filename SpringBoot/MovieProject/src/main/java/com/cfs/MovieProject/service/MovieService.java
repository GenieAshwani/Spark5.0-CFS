package com.cfs.MovieProject.service;


import com.cfs.MovieProject.entity.Movie;
import com.cfs.MovieProject.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies()
    {
       return movieRepository.getAllMovies();
    }

    public Movie getMovieById(Long id)
    {
        return movieRepository.getMovieById(id);
    }

    public Movie addMovie(Movie movie)
    {

        return movieRepository.saveMovie(movie);
    }
    public List<Movie> searchByName(String name)
    {
        return movieRepository.searchByName(name);
    }


}
