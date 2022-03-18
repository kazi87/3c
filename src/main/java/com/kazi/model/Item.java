package com.kazi.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class Item {
    
    protected final Integer id;
    protected final String name;
    protected final Type type;
    protected final BigDecimal price;

    public Item(Integer id, String name, Type type, BigDecimal price) {
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

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Extra.class.getSimpleName() + "[", "]")
                        .add("id=" + id)
                        .add("name='" + name + "'")
                        .add("price=" + price)
                        .add("(type=" + type.name() + ")")
                        .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Extra extra = (Extra) o;
        return id.equals(extra.id) && name.equals(extra.name) && price.equals(extra.price) && type == extra.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, type);
    }
}
