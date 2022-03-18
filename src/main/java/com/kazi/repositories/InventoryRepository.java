package com.kazi.repositories;

import com.kazi.model.Extra;
import com.kazi.model.Offering;
import com.kazi.model.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Simple Inventory repository implementation used to fetch details about available items.
 */
public class InventoryRepository {

    private final static List<Extra> EXTRAS = List.of(
                    new Extra(1, "Extra Milk", Type.COFFEE, new BigDecimal("0.30")),
                    new Extra(2, "Foamed milk", Type.COFFEE, new BigDecimal("0.50")),
                    new Extra(3, "Special roast coffee", Type.COFFEE, new BigDecimal("0.90"))

    );
    public static List<Offering> OFFERINGS = List.of(
                    new Offering(1, "Small Coffee", Type.COFFEE, new BigDecimal("2.50")),
                    new Offering(2, "Medium Coffee", Type.COFFEE, new BigDecimal("3.00")),
                    new Offering(3, "Large Coffee", Type.COFFEE, new BigDecimal("3.50")),
                    new Offering(4, "Bacon Roll", Type.ROLL, new BigDecimal("4.50")),
                    new Offering(5, "Freshly squeezed orange juice", Type.JUICE, new BigDecimal("3.95"))
    );

    public List<Extra> getExtras(List<Integer> ids) {
        List<Extra> result = EXTRAS.stream()
                        .filter(e -> ids.contains(e.getId()))
                        .collect(Collectors.toList());
        if (result.size() != ids.size()) {
            throw new IllegalArgumentException("Missing Extras!");
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
