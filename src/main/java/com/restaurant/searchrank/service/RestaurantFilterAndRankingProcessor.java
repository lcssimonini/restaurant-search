package com.restaurant.searchrank.service;

import com.restaurant.searchrank.domain.Restaurant;
import com.restaurant.searchrank.request.FilterRequest;
import com.restaurant.searchrank.util.RestaurantLoader;

import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant.searchrank.builder.ComparatorBuilder.buildComparator;
import static com.restaurant.searchrank.builder.FilterBuilder.buildPredicateChain;

public class RestaurantFilterAndRankingProcessor implements RestaurantFilterService {

    @Override
    public List<Restaurant> filterRestaurants(FilterRequest request) {
        List<Restaurant> allRestaurants = RestaurantLoader.loadRestaurants();

        return allRestaurants.stream()
                .filter(buildPredicateChain(request))
                .sorted(buildComparator())
                .collect(Collectors.toList());
    }
}
