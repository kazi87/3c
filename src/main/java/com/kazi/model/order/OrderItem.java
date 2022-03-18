package com.kazi.model.order;

import java.util.List;
import java.util.Objects;

public class OrderItem {
    private final Integer offeringId;
    private final List<Integer> extras;

    public OrderItem(Integer offeringId, List<Integer> extrasIds) {
        this.offeringId = offeringId;
        this.extras = extrasIds;
    }

    public Integer getOfferingId() {
        return offeringId;
    }

    public List<Integer> getExtras() {
        return extras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return offeringId.equals(orderItem.offeringId) && Objects.equals(extras, orderItem.extras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offeringId, extras);
    }
}
