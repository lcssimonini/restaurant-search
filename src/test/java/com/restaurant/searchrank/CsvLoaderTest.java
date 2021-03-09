package com.restaurant.searchrank;

import com.restaurant.searchrank.domain.Cuisine;
import com.restaurant.searchrank.domain.Restaurant;
import com.restaurant.searchrank.util.RestaurantLoader;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvLoaderTest {

    @Test
    public void shouldLoadCsv () {
        Map<String, Cuisine> cuisines = RestaurantLoader.loadCuisines();
        assertEquals(19, cuisines.entrySet().size());

        List<Restaurant> restaurants = RestaurantLoader.loadRestaurants();
        assertEquals(200, restaurants.size());
    }
}
