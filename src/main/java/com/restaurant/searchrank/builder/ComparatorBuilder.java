package com.restaurant.searchrank.builder;

import com.restaurant.searchrank.domain.Restaurant;
import lombok.experimental.UtilityClass;

import java.util.Comparator;

@UtilityClass
public class ComparatorBuilder {

    public static Comparator<Restaurant> buildComparator() {
        return (r1, r2) -> {
            int comparation;
            comparation = r1.getDistance().compareTo(r2.getDistance());
            if (comparation == 0) {
                comparation = r2.getRating().compareTo(r1.getRating());
            } if (comparation == 0) {
                comparation = r1.getPrice().compareTo(r2.getPrice());
            }

            return comparation;
        };
    }
}
