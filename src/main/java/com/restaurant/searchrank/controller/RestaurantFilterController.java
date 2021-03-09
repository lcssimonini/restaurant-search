package com.restaurant.searchrank.controller;

import com.restaurant.searchrank.domain.Restaurant;
import com.restaurant.searchrank.request.FilterRequest;
import com.restaurant.searchrank.service.RestaurantFilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantFilterController {

    private final RestaurantFilterService filterProcessor;

    @GetMapping
    public List<Restaurant> findRestaurants(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "cuisine", required = false) String cuisine,
            @RequestParam(value = "rating", required = false) Integer rating,
            @RequestParam(value = "distance", required = false) Integer distance,
            @RequestParam(value = "price", required = false) Integer price) {

        FilterRequest filter = FilterRequest.builder()
                .name(name)
                .cuisine(cuisine)
                .rating(rating)
                .distance(distance)
                .price(price)
                .build();
        log.info("Searching for restaurants with parameters: {}", filter);
        return filterProcessor.filterRestaurants(filter);
    }
}
