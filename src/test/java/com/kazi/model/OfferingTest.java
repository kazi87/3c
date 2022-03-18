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
                        Arguments.of(1, Type.COFFEE, new BigDecimal("-1.00"), "Price can not be negative"),
                        Arguments.of(1, null, new BigDecimal("1.00"), "Type can not be null"),
                        Arguments.of(null, Type.COFFEE, new BigDecimal("1.00"), "Id has to be positive"),
                        Arguments.of(-1, Type.COFFEE, new BigDecimal("1.00"), "Id has to be positive"),
                        Arguments.of(0, Type.COFFEE, new BigDecimal("1.00"), "Id has to be positive")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidInput")
    void invalid(Integer id, Type type, BigDecimal price, String message) {
        //  when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Offering(id, type, price));
        assertEquals(message, ex.getMessage());
    }

}
