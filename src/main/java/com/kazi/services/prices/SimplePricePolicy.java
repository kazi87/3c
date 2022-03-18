package com.kazi.services.prices;

import com.kazi.model.Extra;
import com.kazi.model.Offering;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SimplePricePolicy implements PricePolicy {

    @Override
    public BigDecimal calculate(List<Offering> offerings, List<Extra> extras) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (offerings != null) {
            for (Offering offering : offerings) {
                totalPrice = totalPrice.add(offering.getPrice());
            }
        }
        if (extras != null) {
            for (Extra extra : extras) {
                totalPrice = totalPrice.add(extra.getPrice());
            }
        }
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

}
