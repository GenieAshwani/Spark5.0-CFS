package com.cfs.MovieProject.repository;


import com.cfs.MovieProject.entity.Movie;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    private final Map<Long, Movie> movieDb=new HashMap<>();

    public MovieRepository() {
        System.out.println("default MovieRepository");
    }

    @PostConstruct
    public void loadDummyData()
    {
        System.out.println("loading loadDummyData...");
        movieDb.put(1L,new Movie(1L,"KGF","Action",9.8,2022,"Hindi"));
        movieDb.put(2L,new Movie(2L,"Pushpa","Action romance",9.2,2023,"Tamil"));
        movieDb.put(3L,new Movie(3L,"Bahubali","Action Drama",8.1,2021,"Telugu"));
        movieDb.put(4L,new Movie(4L,"Avatar","Sc-Fi",8.2,2011,"English"));
    }

    public List<Movie> getAllMovies()
    {
        return new ArrayList<>(movieDb.values());
    }

    public Movie getMovieById(Long id)
    {
        return movieDb.get(id);
    }

    public Movie saveMovie(Movie movie)
    {
        movieDb.put(movie.getId(), movie);
        return movie;
    }

    public List<Movie> searchByName(String name)
    {
       /* return movieDb.values()
                .stream()
                .filter(movie -> movie.getName()!=null &&
                        movie.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());*/

        List<Movie> movieList=new ArrayList<>();
        for(Movie collection:movieDb.values())
        {
            if(collection.getName().equals(name))
            {
                movieList.add(collection) ;
            }
        }
        return movieList;
    }

}
