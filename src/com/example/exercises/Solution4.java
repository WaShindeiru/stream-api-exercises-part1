package com.example.exercises;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.City;

import java.util.Collection;
import java.util.Comparator;

//Find the highest populated capital city
public class Solution4 {
    private static InMemoryWorldDao provider = InMemoryWorldDao.getInstance();

    public static void main(String[] args) {
        var countries = provider.findAllCountries();

        var city = countries
                .stream()
                .map((country) -> country.getCities().stream().filter((city1) -> city1.getId() == country.getCapital()))
                .flatMap((a) -> a)
                .max(new Comparator<City>() {
                    @Override
                    public int compare(City city, City t1) {
                        return city.getPopulation() - t1.getPopulation();
                    }
                });
        System.out.println(city.isPresent() ? city.get() : null);
    }
}
