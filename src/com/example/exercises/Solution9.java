package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.domain.Country;

import java.util.Comparator;

//Sort the countries by their population densities in descending order ignoring zero population
//countries
public class Solution9 {

    public static void main(String[] args) {
        var provider = InMemoryWorldDao.getInstance();

        var sortedByPopulation = provider.findAllCountries()
                .stream()
                .filter((country) -> country.getPopulation() != 0)
                .sorted(new Comparator<Country>() {
                    @Override
                    public int compare(Country o1, Country o2) {
                        return (int)(o2.getPopulation()/o2.getSurfaceArea() - o1.getPopulation()/o1.getSurfaceArea());
                    }
                }).toList();

        System.out.println(sortedByPopulation);
    }
}
