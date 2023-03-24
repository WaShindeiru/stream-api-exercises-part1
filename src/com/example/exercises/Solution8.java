package com.example.exercises;

import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;

import java.util.stream.Collectors;

//Group the movies by the year and list them
public class Solution8 {
    public static void main(String[] args) {
        var provider = InMemoryMovieService.getInstance();

        var GroupedMovies = provider.findAllMovies()
                .stream()
                .collect(Collectors.groupingBy(Movie::getYear));

        System.out.println(GroupedMovies);
    }
}
