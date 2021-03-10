package com.restaurant.searchrank;

import com.restaurant.searchrank.exception.InvalidFieldException;
import org.junit.jupiter.api.Test;

import static com.restaurant.searchrank.util.ValidationUtil.validateStringField;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationUtilTest {

    @Test
    public void shouldValidateInput() {
        String value01 = "";
        String value02 = null;
        String value03 = "correct value";

        String errorMEssage = "incorrect field message";

        assertThrows(InvalidFieldException.class,
                () -> validateStringField(value01, errorMEssage));

        assertDoesNotThrow(() -> validateStringField(value02, errorMEssage));

        assertDoesNotThrow(() -> validateStringField(value03, errorMEssage));
    }
}
