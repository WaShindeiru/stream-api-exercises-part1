package com.example.exercises;

import com.example.domain.Genre;
import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//Find the list of movies having the genres "Drama" and "Comedy" only
public class Solution7 {
    public static void main(String[] args) {
        var provider = InMemoryMovieService.getInstance();

        var movies = provider.findAllMovies();

//        var genres = movies
//                .stream()
//                .map((movie) -> movie.getGenres())
//                .flatMap(Collection::stream)
//                .filter((genre) -> (genre.getName().equals("Drama") || genre.getName().equals("Comedy")))
//                .collect(Collectors.toList());
//
//        System.out.println(genres);

        Set<Genre> genres = new HashSet<>();
        genres.add(new Genre(1, "Comedy"));
        genres.add(new Genre(2, "Drama"));

        List<Movie> DramaComedy = movies
                .stream()
                .filter((movie) -> {
                    List<Genre> genreList = movie.getGenres();
                    if(genreList.size() != 2)
                        return false;

                    for(var genre : genreList){
                        if(genre.getName() != "Comedy" && genre.getName() != "Drama"){
                            return false;
                        }
                    }
                    return true;
                }).toList();

        System.out.println(DramaComedy.size());
    }
}
