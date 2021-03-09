package com.restaurant.searchrank;

import com.restaurant.searchrank.exception.InvalidFilterException;
import com.restaurant.searchrank.request.FilterRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class filterRequestTest {

    @Test
    public void shuldBuildValidObject() {
        FilterRequest request = FilterRequest.builder().build();

        assertNotNull(request);

        assertThrows(InvalidFilterException.class,
                () -> FilterRequest.builder().name("").build());

        assertThrows(InvalidFilterException.class,
                () -> FilterRequest.builder().cuisine("").build());

        assertThrows(InvalidFilterException.class,
                () -> FilterRequest.builder().distance(100).build());

        assertThrows(InvalidFilterException.class,
                () -> FilterRequest.builder().price(100).build());

        assertThrows(InvalidFilterException.class,
                () -> FilterRequest.builder().rating(0).build());
    }
}
