package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.domain.Country;

import java.util.Collections;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;


//Sort the countries by number of their cities in descending order
public class Solution6 {
    public static void main(String[] args) {
        InMemoryWorldDao provider = InMemoryWorldDao.getInstance();

        var Countries = provider.findAllCountries()
                .stream()
                .map((c) -> new CountryCityCountPair(c, c.getCities().size()))
                .sorted(Comparator.comparing(CountryCityCountPair::count).reversed())
                .collect(Collectors.toList());

        System.out.println(Countries);
    }
}
