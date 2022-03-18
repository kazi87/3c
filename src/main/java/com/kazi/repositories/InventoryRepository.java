package com.kazi.repositories;

import com.kazi.model.Extra;
import com.kazi.model.Offering;
import com.kazi.model.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple Inventory repository implementation used to fetch details about available items.
 */
public class InventoryRepository {

    private final static List<Extra> EXTRAS = List.of(
                    new Extra(1, "Extra Milk", new BigDecimal("0.30"), Type.COFFEE),
                    new Extra(2, "Foamed milk", new BigDecimal("0.50"), Type.COFFEE),
                    new Extra(3, "Special roast coffee", new BigDecimal("0.90"), Type.COFFEE)

    );
    public static List<Offering> OFFERINGS = List.of(
                    new Offering(1, Type.COFFEE, "small", new BigDecimal("2.50")),
                    new Offering(2, Type.COFFEE, "medium", new BigDecimal("3.00")),
                    new Offering(3, Type.COFFEE, "large", new BigDecimal("3.50")),
                    new Offering(4, Type.ROLL, new BigDecimal("4.50")),
                    new Offering(5, Type.JUICE, new BigDecimal("3.95"))
    );

    public List<Extra> getExtras(List<Integer> ids) {
        List<Extra> result = EXTRAS.stream()
                        .filter(e -> ids.contains(e.getId()))
                        .collect(Collectors.toList());
        if (result.size() != ids.size()) {
            throw new IllegalArgumentException("Requested not existing extras");
        }
        return result;
    }

    public Optional<Offering> getOffering(Integer id) {
        return OFFERINGS.stream().filter(o -> o.getId() == id).findFirst();
    }

    public List<Offering> getOfferings() {
        return OFFERINGS;
    }

    public List<Extra> getExtras() {
        return EXTRAS;
    }
}
