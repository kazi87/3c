package com.kazi.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public final class Offering {

    private final Integer id;

    private final String name;

    private final Type type;

    private final BigDecimal price;

    public Offering(Integer id, String name, Type type, BigDecimal price) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id has to be positive");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price nas to be a positive number");
        }
        if (type == null) {
            throw new IllegalArgumentException("Type can not be null");
        }

        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                        .add("id=" + id)
                        .add("name='" + name + "'")
                        .add("type=" + type)
                        .add("price=" + price)
                        .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offering offering = (Offering) o;
        return id.equals(offering.id) && name.equals(offering.name) && type == offering.type && price.equals(offering.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price);
    }
}
