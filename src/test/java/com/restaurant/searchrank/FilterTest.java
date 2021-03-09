package com.restaurant.searchrank;

import com.restaurant.searchrank.domain.Restaurant;
import com.restaurant.searchrank.request.FilterRequest;
import com.restaurant.searchrank.service.RestaurantFilterAndRanking;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {

    private RestaurantFilterAndRanking filterAndRanking = new RestaurantFilterAndRanking();

    @Test
    public void shouldFilterByName() {
        FilterRequest request = FilterRequest.builder().name("delicious").build();

        List<Restaurant> filtered = filterAndRanking.filterRestaurants(request);
        assertEquals(24, filtered.size());
    }
}
