package com.example.solutions;

import com.example.domain.Director;
import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution2 {
    private static final MovieService movieService = InMemoryMovieService.getInstance();

    public static void main(String[] args) {
        // Find the number of movies of each director
        var movies = movieService.findAllMovies();

        Map<String, Long> directorMap = movies.stream()
                .map(Movie::getDirectors)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        Director::getName,
                        Collectors.counting()
                ));

        System.out.println(directorMap);
    }
}
