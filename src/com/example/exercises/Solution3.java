package com.example.exercises;

import com.example.domain.Director;
import com.example.domain.Genre;
import com.example.domain.Movie;
import com.example.exercises.DirectorGenresPair;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

//Find the number of genres of each director's movies
public class Solution3 {
    private static MovieService movieService = InMemoryMovieService.getInstance();

    public static void main(String[] args) {
        var movies = movieService.findAllMovies();
        var GenreMap = movies
                .stream()
                .map((Movie movie) -> movie.getDirectors().stream().map((Director d) -> new DirectorGenresPair(d, movie.getGenres())).toList())
                .flatMap(Collection::stream)
                .map(Pair -> Pair.genres().stream().map(genre -> new DirectorGenrePair(Pair.director(), genre)).toList())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(DirectorGenrePair::director,
                        Collectors.groupingBy(DirectorGenrePair::genre,
                                Collectors.counting())));

        GenreMap.forEach((Director d, Map<Genre, Long> m) -> {
            System.out.println(d.getName());
            m.forEach((Genre g, Long l) -> {
                System.out.println("\t" + g.getName() + ": " + l);
            });
        });
    }
}
