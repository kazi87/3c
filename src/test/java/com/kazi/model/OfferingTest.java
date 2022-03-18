package com.kazi.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OfferingTest {

    private static Stream<Arguments> invalidInput() {
        return Stream.of(
                        Arguments.of(1, "test", Type.COFFEE, new BigDecimal("-1.00"), "Price nas to be a positive number"),
                        Arguments.of(1, "test", Type.COFFEE, null, "Price nas to be a positive number"),
                        Arguments.of(1, "test", null, new BigDecimal("1.00"), "Type can not be null"),
                        Arguments.of(null, "test", Type.COFFEE, new BigDecimal("1.00"), "Id has to be positive"),
                        Arguments.of(-1, "test", Type.COFFEE, new BigDecimal("1.00"), "Id has to be positive"),
                        Arguments.of(0, "test", Type.COFFEE, new BigDecimal("1.00"), "Id has to be positive"),
                        Arguments.of(1, "", Type.COFFEE, new BigDecimal("1.00"), "Invalid name"),
                        Arguments.of(1, null, Type.COFFEE, new BigDecimal("1.00"), "Invalid name")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidInput")
    void invalid(Integer id, String name, Type type, BigDecimal price, String message) {
        //  when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Offering(id, name, type, price));
        assertEquals(message, ex.getMessage());
    }

}
