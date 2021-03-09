package com.restaurant.searchrank.util;

import com.restaurant.searchrank.domain.Cuisine;
import com.restaurant.searchrank.domain.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
public class RestaurantLoader {

    private static final String[] CUISINES_HEADERS = { "id", "name"};
    private static final String[] RESTAURANTS_HEADERS = { "name", "customer_rating", "distance", "price", "cuisine_id" };

    private static final String CUISINES_CSV = "classpath:cuisines.csv";
    private static final String RESTAURANTS_CSV = "classpath:restaurants.csv";

    public static List<Restaurant> loadRestaurants() {
        Iterable<CSVRecord> records = null;
        try {
            records = getCsvRecords(RESTAURANTS_HEADERS, RESTAURANTS_CSV);
        } catch (IOException e) {
            log.error("Error reading file");
        }

        return Optional.ofNullable(records).map(mapRestaurants(loadCuisines()))
                .orElse(new ArrayList<>());
    }

    public static Map<String, Cuisine> loadCuisines() {
        Iterable<CSVRecord> records = null;
        try {
            records = getCsvRecords(CUISINES_HEADERS, CUISINES_CSV);
        } catch (IOException e) {
            log.error("Error reading file");
        }

        return Optional.ofNullable(records).map(mapCuisines())
                .orElse(new HashMap<>());
    }

    private static Function<Iterable<CSVRecord>, Map<String, Cuisine>> mapCuisines() {
        return rs -> StreamSupport.stream(rs.spliterator(), false)
                .map(RestaurantLoader::buildCuisine)
                .collect(Collectors.toMap(Cuisine::getId, Function.identity()));
    }

    private static Cuisine buildCuisine(CSVRecord record) {
        return Cuisine.builder()
                .id(record.get(CUISINES_HEADERS[0]))
                .name(record.get(CUISINES_HEADERS[1]))
                .build();
    }

    private static Function<Iterable<CSVRecord>, List<Restaurant>> mapRestaurants(Map<String, Cuisine> cuisines) {
        return rs -> StreamSupport.stream(rs.spliterator(), false)
                .map(r -> buildRestaurant(cuisines, r))
                .collect(Collectors.toList());
    }

    private static Restaurant buildRestaurant(Map<String, Cuisine> cuisines, CSVRecord record) {
        Cuisine cuisine = getCuisineById(cuisines, record.get(RESTAURANTS_HEADERS[4]));
        return Restaurant.builder()
                .name(record.get(RESTAURANTS_HEADERS[0]))
                .rating(Integer.valueOf(record.get(RESTAURANTS_HEADERS[1])))
                .distance(Integer.valueOf(record.get(RESTAURANTS_HEADERS[2])))
                .price(Integer.valueOf(record.get(RESTAURANTS_HEADERS[3])))
                .cuisine(cuisine.getName())
                .build();
    }

    private static Cuisine getCuisineById(Map<String, Cuisine> cuisines, String id) {
        return Optional.ofNullable(cuisines.get(id))
                .orElse(Cuisine.builder().id("0").name("empty").build());
    }

    private static Iterable<CSVRecord> getCsvRecords(String[] headers, String fileName) throws IOException {
        Reader in = new FileReader(ResourceUtils.getFile(fileName));
        return CSVFormat.DEFAULT
                .withHeader(headers)
                .withFirstRecordAsHeader()
                .parse(in);
    }
}
