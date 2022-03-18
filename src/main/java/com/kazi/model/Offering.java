package com.kazi.model;

import java.math.BigDecimal;

public final class Offering extends Item {

    public Offering(Integer id, String name, Type type, BigDecimal price) {
        super(id, name, type, price);
    }
}
