package com.kazi;

import com.kazi.model.order.Order;
import com.kazi.model.order.OrderItem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple parser transforming input data to an {@link Order}.
 */
public class OrderInputParser {

    /**
     * Parses input order in a form of ids, where first id is an offering id and all other are extras Ids
     * <p>
     * Sample input: ["1,1,3" "4,1" "6"]
     *
     * @param input - list of comma separated ids, the first one is an offering ID, the others are extras Ids
     * @return Order generated based on the given input
     */
    public Order parse(String[] input) {
        if (input.length == 0) {
            throw new IllegalArgumentException("Invalid empty input - item has to contain at least offering Id");
        }

        List<OrderItem> items = Arrays.asList(input).stream()
                        .map(this::parseSingleItem)
                        .map(this::toOrderItem)
                        .collect(Collectors.toList());
        return new Order(items);
    }

    private OrderItem toOrderItem(List<Integer> ids) {
        List<Integer> extrasIds = ids.size() > 1
                        ? ids.subList(1, ids.size())
                        : List.of();
        return new OrderItem(ids.get(0), extrasIds);
    }

    private List<Integer> parseSingleItem(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("Invalid empty list - item has to contain at least offering Id");
        }
        String[] ids = s.split(",");
        return Arrays.asList(ids).stream()
                        .map(String::trim)
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());
    }
}
