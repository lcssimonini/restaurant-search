package com.restaurant.searchrank.builder;

import com.restaurant.searchrank.domain.Restaurant;
import lombok.experimental.UtilityClass;

import java.util.Comparator;

@UtilityClass
public class ComparatorBuilder {

    public static Comparator<Restaurant> buildComparator() {
        return Comparator.comparing(Restaurant::getDistance).reversed()
                .thenComparing(Restaurant::getRating)
                .thenComparing(Restaurant::getPrice).reversed();
    }
}
