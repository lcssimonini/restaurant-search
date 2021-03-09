package com.restaurant.searchrank.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cuisine {

    private String id;
    private String name;
}
