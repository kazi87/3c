package com.kazi;

import com.kazi.model.order.Order;
import com.kazi.model.order.OrderItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderInputParserTest {
    private OrderInputParser testObj = new OrderInputParser();

    private static Stream<Arguments> validInput() {
        return Stream.of(
                        Arguments.of(new Order(List.of(new OrderItem(1, List.of(2, 3)))), new String[] { "1,2,3" }),
                        Arguments.of(new Order(List.of(new OrderItem(1, List.of()))), new String[] { "1" }),
                        Arguments.of(new Order(List.of(new OrderItem(1, List.of(2, 2)))), new String[] { "1,2,2" }),
                        Arguments.of(new Order(List.of(new OrderItem(1, List.of(2)))), new String[] { " 1 , 2" }),
                        Arguments.of(new Order(List.of(new OrderItem(1, List.of(2, 3)), new OrderItem(3, List.of()))), new String[] { "1,2,3", "3" })
        );
    }

    @ParameterizedTest
    @MethodSource("validInput")
    void parseValidOrders(Order order, String[] input) {
        //  when
        Order result = testObj.parse(input);
        //  then
        assertIterableEquals(order.getItems(), result.getItems());
        assertNotNull(result.getOrderDate());
        assertNotNull(result.getId());
    }

    @Test
    void shouldThrowExceptionWhenEmptyList() {
        //  given
        String input[] = new String[] { "" };
        //  when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> testObj.parse(input));
        //  then
        assertEquals("Invalid empty list - item has to contain at least offering Id", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNotNumeric() {
        //  given
        String input[] = new String[] { "a" };
        //  when
        NumberFormatException ex = assertThrows(NumberFormatException.class, () -> testObj.parse(input));
        //  then
        assertEquals("For input string: \"a\"", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmptyInput() {
        //  given
        String input[] = new String[] {};
        //  when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> testObj.parse(input));
        //  then
        assertEquals("Invalid empty input - item has to contain at least offering Id", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNullList() {
        //  given
        String input[] = new String[] { null };
        //  when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> testObj.parse(input));
        //  then
        assertEquals("Invalid empty list - item has to contain at least offering Id", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMissingId() {
        //  given
        String input[] = new String[] { ",1," };
        //  when
        IllegalArgumentException ex = assertThrows(NumberFormatException.class, () -> testObj.parse(input));
        //  then
        assertEquals("For input string: \"\"", ex.getMessage());
    }

}
