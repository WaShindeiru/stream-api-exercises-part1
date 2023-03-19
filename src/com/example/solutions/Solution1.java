package com.example.solutions;

import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;
import com.example.domain.Country;
import com.example.exercises.ContinentCityPair;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution1 {
    private static final CountryDao countryDao = InMemoryWorldDao.getInstance();

    public static void main(String[] args){
        var buffer = countryDao.findAllCountries();
        var ContinentMap = buffer
                .stream()
                .map((Country a) -> a.getCities().stream().map((City c) -> new ContinentCityPair(a.getContinent(), c)).toList())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        ContinentCityPair::continent,
                        Collectors.maxBy(ContinentCityPair::compareTo)
                ));
        ContinentMap.forEach(ContinentCityPair::printEntry);
    }
}
