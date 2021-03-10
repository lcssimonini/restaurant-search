package com.restaurant.searchrank;

import org.junit.jupiter.api.Test;

import static com.restaurant.searchrank.util.StringUtil.normalize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {

    @Test
    public void shouldNormalizeString() {
        String input01 = "A name ";
        String input02 = "egocêntrico";
        String input03 = "cuisine ";
        String input04 = "  Restaurant ";
        String input05 = "     ";

        assertEquals("A NAME", normalize(input01));
        assertEquals("EGOCENTRICO", normalize(input02));
        assertEquals("CUISINE", normalize(input03));
        assertEquals("RESTAURANT", normalize(input04));
        assertEquals("", normalize(input05));
    }
}
