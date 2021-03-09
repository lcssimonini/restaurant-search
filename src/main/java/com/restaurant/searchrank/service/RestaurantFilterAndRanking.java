package com.restaurant.searchrank.service;

import com.restaurant.searchrank.domain.Restaurant;
import com.restaurant.searchrank.request.FilterRequest;
import com.restaurant.searchrank.util.RestaurantLoader;

import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant.searchrank.domain.Restaurant.nameMatches;

public class RestaurantFilterAndRanking implements RestaurantFilterService {

    @Override
    public List<Restaurant> filterRestaurants(FilterRequest request) {
        List<Restaurant> allRestaurants = RestaurantLoader.loadRestaurants();

        return allRestaurants.stream()
                .filter(nameMatches(request.getName()))
                .collect(Collectors.toList());
    }
}
