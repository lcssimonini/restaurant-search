package com.restaurant.searchrank.service;

import com.restaurant.searchrank.domain.Restaurant;
import com.restaurant.searchrank.request.FilterRequest;

import java.util.List;

public interface RestaurantFilterService {

    List<Restaurant> filterRestaurants(FilterRequest request);
}
