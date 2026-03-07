package com.cfs.MovieProject.controller;


import com.cfs.MovieProject.entity.Movie;
import com.cfs.MovieProject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class StudentController {

    @Autowired
    private  MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies()
    {
        System.out.println("all movies...");
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id)
    {
        return movieService.getMovieById(id);
    }

    @GetMapping("/search")
    public List<Movie> searchMovieByName(@RequestParam String name)
    {
        System.out.println("/search");
        return movieService.searchByName(name);
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie)
    {
        System.out.println("saving movie...");

        return movieService.addMovie(movie);
    }

}
