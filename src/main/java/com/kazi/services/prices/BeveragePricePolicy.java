package com.kazi.services.prices;

import com.kazi.model.Extra;
import com.kazi.model.Offering;

import java.math.BigDecimal;
import java.util.List;

public class BeveragePricePolicy implements PricePolicy {

    @Override
    public BigDecimal calculate(List<Offering> offerings, List<Extra> extras) {
        // TODO - Bonus Program, not available yet :)
        return BigDecimal.ZERO;
    }

}
