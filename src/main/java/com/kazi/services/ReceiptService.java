package com.kazi.services;

import com.kazi.model.Extra;
import com.kazi.model.Offering;
import com.kazi.model.Type;
import com.kazi.model.order.Order;
import com.kazi.model.order.OrderItem;
import com.kazi.repositories.InventoryRepository;
import com.kazi.services.prices.PricePolicy;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ReceiptService {

    private final InventoryRepository inventoryRepository;

    public ReceiptService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Naively implemented "String" receipt generator, based on a given order
     */
    public String generateReceipt(Order order, PricePolicy pricePolicy) {
        StringBuilder receipt = new StringBuilder("------ RECEIPT -------")
                        .append("\n")
                        .append("Order ID: " + order.getId())
                        .append("\n")
                        .append("Order Date: " + order.getOrderDate().format(DateTimeFormatter.ISO_DATE))
                        .append("\n")
                        .append("Order Time: " + order.getOrderDate().toLocalTime().truncatedTo(ChronoUnit.SECONDS).toString())
                        .append("\n")
                        .append("------ Items -------")
                        .append("\n");

        //  needed to calculate total price
        List<Extra> totalExtras = new LinkedList<>();
        List<Offering> totalOfferings = new LinkedList<>();

        for (OrderItem item : order.getItems()) {
            //  offering
            Offering offering = inventoryRepository.getOffering(item.getOfferingId())
                            .orElseThrow(() -> new IllegalStateException("Invalid offering ID: " + item.getOfferingId()));

            totalOfferings.add(offering);
            receipt.append(formatOffering(offering));
            receipt.append("\n");

            //  extras
            List<Extra> extras = getExtras(item, offering.getType());
            totalExtras.addAll(extras);
            extras.stream().map(this::formatExtra).map(a -> a + "\n").forEach(receipt::append);

        }
        receipt.append("------ TOTAL -------")
                        .append("\n");
        receipt.append("Total Price: " + pricePolicy.calculate(totalOfferings, totalExtras));
        pricePolicy.getPolicyInfo().ifPresent(receipt::append);

        return receipt.toString();
    }

    private List<Extra> getExtras(OrderItem item, Type type) {
        List<Extra> extras = inventoryRepository.getExtras(item.getExtras());

        //  Type validation
        Optional<Extra> incompatible = extras.stream().filter(e -> !e.getType().equals(type)).findFirst();
        if (incompatible.isPresent()) {
            throw new IllegalArgumentException("Found at east 1 incompatible extra for " + type.name() + ": " + incompatible.get());
        }
        return extras;
    }

    private String formatExtra(Extra e) {
        return String.format("   -> %s:  %s CHF", e.getName(), e.getPrice());
    }

    private String formatOffering(Offering offering) {
        return String.format("%s: %s CHF", offering.getName(), offering.getPrice());
    }

}
