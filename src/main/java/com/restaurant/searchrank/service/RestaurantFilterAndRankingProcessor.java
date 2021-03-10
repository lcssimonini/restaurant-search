package com.restaurant.searchrank.service;

import com.restaurant.searchrank.domain.Restaurant;
import com.restaurant.searchrank.request.FilterRequest;
import com.restaurant.searchrank.util.RestaurantLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.restaurant.searchrank.builder.ComparatorBuilder.buildComparator;
import static com.restaurant.searchrank.builder.FilterBuilder.buildPredicateChain;

@Component
public class RestaurantFilterAndRankingProcessor implements RestaurantFilterService {

    @Value("${search.matches.max:5}")
    private int maxMatches;
    private final List<Restaurant> allRestaurants = RestaurantLoader.loadRestaurants();

    @Override
    public List<Restaurant> filterRestaurants(FilterRequest request) {
        return allRestaurants.stream()
                .filter(buildPredicateChain(request))
                .sorted(buildComparator())
                .limit(maxMatches)
                .collect(Collectors.toList());
    }
}
