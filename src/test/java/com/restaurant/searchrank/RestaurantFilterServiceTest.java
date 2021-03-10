package com.restaurant.searchrank;

import com.restaurant.searchrank.domain.Restaurant;
import com.restaurant.searchrank.request.FilterRequest;
import com.restaurant.searchrank.service.RestaurantFilterService;
import com.restaurant.searchrank.util.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.restaurant.searchrank.util.StringUtil.normalize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {SearchRankApplication.class})
public class RestaurantFilterServiceTest {

    @Autowired
    private RestaurantFilterService restaurantFilterService;

    @Test
    public void shouldFilterByName() {
        FilterRequest request = FilterRequest.builder().name("delicious").build();

        List<Restaurant> filtered = restaurantFilterService.filterRestaurants(request);
        assertEquals(5, filtered.size());
        checkNames(request, filtered);
    }

    @Test
    public void shouldFilterByNameAndRating() {
        FilterRequest request = FilterRequest.builder().name("delicious").rating(4).build();

        List<Restaurant> filtered = restaurantFilterService.filterRestaurants(request);
        assertEquals(5, filtered.size());
        checkNames(request, filtered);
        checkRatings(request, filtered);
    }

    @Test
    public void shouldFilterByNameAndRatingAndDistance() {
        FilterRequest request = FilterRequest.builder()
                .name("delicious")
                .rating(4)
                .distance(3)
                .build();

        List<Restaurant> filtered = restaurantFilterService.filterRestaurants(request);
        assertEquals(5, filtered.size());
        checkNames(request, filtered);
        checkDistances(request, filtered);
        checkRatings(request, filtered);
    }

    @Test
    public void shouldFilterByNameAndRatingAndDistanceAndPrice() {
        FilterRequest request = FilterRequest.builder()
                .name("delicious")
                .rating(4)
                .distance(3)
                .price(20)
                .build();

        List<Restaurant> filtered = restaurantFilterService.filterRestaurants(request);
        assertEquals(3, filtered.size());
        checkNames(request, filtered);
        checkDistances(request, filtered);
        checkRatings(request, filtered);
        checkPrices(request, filtered);
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

        List<Restaurant> filtered = restaurantFilterService.filterRestaurants(request);
        assertEquals(1, filtered.size());
        Restaurant restaurant = filtered.get(0);
        assertTrue(normalize(restaurant.getCuisine()).contains(normalize(request.getCuisine())));
        assertTrue(normalize(restaurant.getName()).contains(normalize(request.getName())));
        assertTrue(restaurant.getPrice().compareTo(request.getPrice()) <= 0);
        assertTrue(restaurant.getRating().compareTo(request.getRating()) >= 0);
        assertTrue(restaurant.getDistance().compareTo(request.getDistance()) <= 0);
        checkNames(request, filtered);
        checkCuisines(request, filtered);
        checkDistances(request, filtered);
        checkRatings(request, filtered);
        checkPrices(request, filtered);
    }

    private void checkDistances(FilterRequest request, List<Restaurant> filtered) {
        assertThat(filtered.stream().map(Restaurant::getDistance).collect(Collectors.toList()), everyItem(lessThanOrEqualTo(request.getDistance())));
    }

    private void checkRatings(FilterRequest request, List<Restaurant> filtered) {
        assertThat(filtered.stream().map(Restaurant::getRating).collect(Collectors.toList()), everyItem(greaterThanOrEqualTo(request.getRating())));
    }

    private void checkPrices(FilterRequest request, List<Restaurant> filtered) {
        assertThat(filtered.stream().map(Restaurant::getPrice).collect(Collectors.toList()), everyItem(lessThanOrEqualTo(request.getPrice())));
    }

    private void checkNames(FilterRequest request, List<Restaurant> filtered) {
        assertThat(normalizedNames(filtered), everyItem(containsString(normalize(request.getName()))));
    }

    private void checkCuisines(FilterRequest request, List<Restaurant> filtered) {
        assertThat(normalizedCuisines(filtered), everyItem(containsString(normalize(request.getCuisine()))));
    }

    private List<String> normalizedNames(List<Restaurant> filtered) {
        return getNormalizedStringFields(filtered.stream()
                .map(Restaurant::getName));
    }

    private List<String> getNormalizedStringFields(Stream<String> stringStream) {
        return stringStream
                .map(StringUtil::normalize)
                .collect(Collectors.toList());
    }

    private List<String> normalizedCuisines(List<Restaurant> filtered) {
        return getNormalizedStringFields(filtered.stream()
                .map(Restaurant::getCuisine));
    }
}
