package com.restaurant.searchrank.request;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.restaurant.searchrank.util.ValidationUtil.validateStringField;

@Data
@Builder
public class FilterRequest {

    public static final int MAX_DISTANCE = 10;
    public static final int MIN_DISTANCE = 1;
    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;
    private static final int MIN_PRICE = 10;
    private static final int MAX_PRICE = 50;

    private String name;
    private String cuisine;
    private Integer rating;
    private Integer distance;
    private Integer price;

    public Map<String, Object> filterValues() {
        return new HashMap<>() {{
           put("name", name);
           put("cuisine", cuisine);
           put("customerRating", rating);
           put("distance", distance);
           put("price", price);
        }};
    }

    public void validate() {
        validateName();
        validateCuisine();
        validateCustomerRating();
        validateDistance();
        validatePrice();
    }

    private void validatePrice() {
        if (getPrice() != null && !priceIsInRange()) {
            throw new IllegalArgumentException(
                    "Price should be between " + MIN_PRICE + " and " + MAX_PRICE + " dolars");
        }
    }

    private boolean priceIsInRange() {
        return getPrice().compareTo(MIN_PRICE) >= 0 && getPrice().compareTo(MAX_PRICE) <= 0;
    }

    private void validateDistance() {
        if (getDistance() != null && !distanceIsInRange()) {
            throw new IllegalArgumentException(
                    "Distance rating should be between " + MIN_DISTANCE + " and " + MAX_DISTANCE + " miles");
        }
    }

    private boolean distanceIsInRange() {
        return getDistance().compareTo(MIN_DISTANCE) >= 0 && getDistance().compareTo(MAX_DISTANCE) <= 0;
    }

    private void validateCustomerRating() {
        if (getRating() != null && !ratingIsInRAnge()) {
            throw new IllegalArgumentException(
                    "Customer rating should be between " + MIN_RATING + " and " + MAX_RATING + " stars");
        }
    }

    private boolean ratingIsInRAnge() {
        return getRating().compareTo(MIN_RATING) >= 0 && getRating().compareTo(MAX_RATING) <= 0;
    }

    private void validateCuisine() {
        validateStringField(getCuisine(), "cuisine should not be empty");
    }

    private void validateName() {
        validateStringField(getName(), "name should not be empty");
    }
}
