package com.restaurant.searchrank.builder;

import com.restaurant.searchrank.domain.Restaurant;
import com.restaurant.searchrank.request.FilterRequest;
import lombok.experimental.UtilityClass;

import java.util.function.Predicate;

import static com.restaurant.searchrank.domain.Restaurant.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@UtilityClass
public class FilterBuilder {

    public static Predicate<Restaurant> buildPredicateChain(FilterRequest request) {
        Predicate<Restaurant> predicate = new NeutralPredicate();

        if (isNotEmpty(request.getName())) {
            predicate = predicate.and(nameMatches(request.getName()));
        }

        if (isNotEmpty(request.getCuisine())) {
            predicate = predicate.and(cuisineMatches(request.getCuisine()));
        }

        if (request.getRating() != null) {
            predicate = predicate.and(ratingMatches(request.getRating()));
        }

        if (request.getDistance() != null) {
            predicate = predicate.and(distanceMatches(request.getDistance()));
        }

        if (request.getPrice() != null) {
            predicate = predicate.and(priceMatches(request.getPrice()));
        }

        return predicate;
    }

    private static class NeutralPredicate implements Predicate<Restaurant> {

        @Override
        public boolean test(Restaurant restaurant) {
            return true;
        }
    }
}
