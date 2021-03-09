package com.restaurant.searchrank;

import com.restaurant.searchrank.domain.Restaurant;
import com.restaurant.searchrank.request.FilterRequest;
import com.restaurant.searchrank.service.RestaurantFilterAndRanking;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.restaurant.searchrank.util.StringUtil.normalize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterTest {

    private final RestaurantFilterAndRanking filterAndRanking = new RestaurantFilterAndRanking();

    @Test
    public void shouldNotFilter() {
        FilterRequest request = FilterRequest.builder().build();

        List<Restaurant> filtered = filterAndRanking.filterRestaurants(request);
        assertEquals(200, filtered.size());
    }

    @Test
    public void shouldFilterByName() {
        FilterRequest request = FilterRequest.builder().name("delicious").build();

        List<Restaurant> filtered = filterAndRanking.filterRestaurants(request);
        assertEquals(24, filtered.size());
    }

    @Test
    public void shouldFilterByNameAndRating() {
        FilterRequest request = FilterRequest.builder().name("delicious").rating(4).build();

        List<Restaurant> filtered = filterAndRanking.filterRestaurants(request);
        assertEquals(11, filtered.size());
    }

    @Test
    public void shouldFilterByNameAndRatingAndDistance() {
        FilterRequest request = FilterRequest.builder()
                .name("delicious")
                .rating(4)
                .distance(3)
                .build();

        List<Restaurant> filtered = filterAndRanking.filterRestaurants(request);
        assertEquals(5, filtered.size());
    }

    @Test
    public void shouldFilterByNameAndRatingAndDistanceAndPrice() {
        FilterRequest request = FilterRequest.builder()
                .name("delicious")
                .rating(4)
                .distance(3)
                .price(20)
                .build();

        List<Restaurant> filtered = filterAndRanking.filterRestaurants(request);
        assertEquals(3, filtered.size());
    }

    @Test
    public void shouldFilterByNameAndRatingAndDistanceAndPriceAndCuisine() {
        FilterRequest request = FilterRequest.builder()
                .name("delicious")
                .rating(4)
                .distance(3)
                .price(20)
                .cuisine("russian")
                .build();

        List<Restaurant> filtered = filterAndRanking.filterRestaurants(request);
        assertEquals(1, filtered.size());
        Restaurant restaurant = filtered.get(0);
        assertTrue(normalize(restaurant.getCuisine()).contains(normalize(request.getCuisine())));
        assertTrue(normalize(restaurant.getName()).contains(normalize(request.getName())));
        assertTrue(restaurant.getPrice().compareTo(request.getPrice()) <= 0);
        assertTrue(restaurant.getRating().compareTo(request.getRating()) >= 0);
        assertTrue(restaurant.getDistance().compareTo(request.getDistance()) <= 0);
    }
}
