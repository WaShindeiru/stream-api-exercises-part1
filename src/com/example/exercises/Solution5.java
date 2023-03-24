package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.domain.Country;

import java.util.Comparator;
import java.util.Objects;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Find the highest populated capital city of each continent
public class Solution5 {
    public static void main(String[] args) {
        InMemoryWorldDao provider = InMemoryWorldDao.getInstance();
        List<Country> Countries = provider.findAllCountries();

        Object Objects;
        var PopulationMap = Countries
                .stream()
                .map((c) -> new ContinentCityPair(c.getContinent(), provider.findCityById(c.getCapital())))
//                .filter((pair) -> java.util.Objects.nonNull(pair.city()))
                .collect(Collectors.toList());
//                .collect(Collectors.groupingBy(ContinentCityPair::continent, Collectors.maxBy(Comparator.comparing(
//                        (pair) -> pair.city().getPopulation()
//                ))));

        System.out.println(PopulationMap);
    }
}
