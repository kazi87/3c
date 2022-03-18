package com.kazi.services.prices;

import com.kazi.model.Extra;
import com.kazi.model.Offering;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Simple interface giving a possibility to provide a price calculation
 */
public interface PricePolicy {
    BigDecimal calculate(List<Offering> offerings, List<Extra> extras);

    default Optional<String> getPolicyInfo() {
        return Optional.empty();
    }
}
