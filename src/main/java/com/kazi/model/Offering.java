package com.kazi.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public final class Offering {

    private final Integer id;

    private final Type type;

    private final String size;

    private final BigDecimal price;

    public Offering(Integer id, Type type, String size, BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price can not be negative");
        }
        if (type == null) {
            throw new IllegalArgumentException("Type can not be null");
        }

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id has to be positive");
        }

        this.id = id;
        this.type = type;
        this.size = size;
        this.price = price;
    }

    public Offering(Integer id, Type type, BigDecimal price) {
        this(id, type, null, price);
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                        .add("id=" + id)
                        .add("type=" + type)
                        .add("size='" + size + "'")
                        .add("price=" + price)
                        .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offering offering = (Offering) o;
        return id.equals(offering.id) && type == offering.type && size.equals(offering.size) && price.equals(offering.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, size, price);
    }
}
