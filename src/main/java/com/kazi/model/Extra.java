package com.kazi.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class Extra {

    private final Integer id;

    private final String name;

    private final BigDecimal price;

    private final Type type;

    public Extra(Integer id, String name, Type type, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
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
