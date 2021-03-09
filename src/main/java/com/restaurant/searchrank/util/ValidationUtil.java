package com.restaurant.searchrank.util;

import com.restaurant.searchrank.exception.InvalidFilterException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public static void validateStringField(String fieldValue, String message) {
        if (fieldValue != null && fieldValue.trim().isEmpty()) {
            throw new InvalidFilterException(message);
        }
    }
}
