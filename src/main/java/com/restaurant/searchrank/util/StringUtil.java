package com.restaurant.searchrank.util;

import lombok.experimental.UtilityClass;

import static org.apache.commons.lang3.StringUtils.stripAccents;

@UtilityClass
public class StringUtil {

    public static String normalize(String inputString) {
        return stripAccents(inputString.trim().toUpperCase());
    }
}
