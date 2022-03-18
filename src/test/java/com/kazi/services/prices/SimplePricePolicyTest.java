package com.kazi.services.prices;

import com.kazi.model.Extra;
import com.kazi.model.Offering;
import com.kazi.model.Type;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimplePricePolicyTest {

    public static final BigDecimal PRICE_ONE = new BigDecimal("1.00");
    public static final Offering OFFERING = new Offering(1, Type.COFFEE, PRICE_ONE);
    public static final Extra EXTRA = new Extra(1, "test", PRICE_ONE, Type.COFFEE);

    private SimplePricePolicy testObj = new SimplePricePolicy();

    private static Stream<Arguments> input() {
        return Stream.of(
                        Arguments.of(List.of(), List.of(), new BigDecimal("0.00")),
                        Arguments.of(List.of(), null, new BigDecimal("0.00")),
                        Arguments.of(null, List.of(), new BigDecimal("0.00")),
                        Arguments.of(List.of(OFFERING), List.of(), new BigDecimal("1.00")),
                        Arguments.of(List.of(OFFERING), List.of(EXTRA), new BigDecimal("2.00")),
                        Arguments.of(List.of(OFFERING), List.of(EXTRA, EXTRA, EXTRA), new BigDecimal("4.00")),
                        Arguments.of(List.of(new Offering(1, Type.COFFEE, new BigDecimal("1.11"))), List.of(), new BigDecimal("1.11")),
                        Arguments.of(List.of(new Offering(1, Type.COFFEE, new BigDecimal("1.111111"))), List.of(), new BigDecimal("1.11")),
                        Arguments.of(List.of(new Offering(1, Type.COFFEE, new BigDecimal("1"))), List.of(), new BigDecimal("1.00"))

        );
    }

    @ParameterizedTest
    @MethodSource("input")
    void calculate(List<Offering> offerings, List<Extra> extras, BigDecimal expectedPrice) {
        //  given
        //  when
        BigDecimal result = testObj.calculate(offerings, extras);
        //  then
        assertEquals(expectedPrice, result);
    }
}
