package com.restaurant.searchrank.domain;

import lombok.Builder;
import lombok.Data;

import java.util.function.Predicate;

import static com.restaurant.searchrank.util.StringUtil.normalize;

@Data
@Builder
public class Restaurant {

    private String name;
    private String cuisine;
    private Integer distance;
    private Integer price;
    private Integer rating;

    public static Predicate<Restaurant> nameMatches(String searchInput) {
        return restaurant -> normalize(restaurant.getName()).contains(normalize(searchInput));
    }

    public static Predicate<Restaurant> cuisineMatches(String searchInput) {
        return restaurant -> normalize(restaurant.getCuisine()).contains(normalize(searchInput));
    }

    public static Predicate<Restaurant> ratingMatches(Integer ratingInput) {
        return restaurant -> restaurant.getRating().compareTo(ratingInput) >= 0;
    }

    public static Predicate<Restaurant> distanceMatches(Integer distanceInput) {
        return restaurant -> restaurant.getDistance().compareTo(distanceInput) <= 0;
    }

    public static Predicate<Restaurant> priceMatches(Integer priceInput) {
        return restaurant -> restaurant.getPrice().compareTo(priceInput) <= 0;
    }
}
