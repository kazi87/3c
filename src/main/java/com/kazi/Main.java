package com.kazi;

import com.kazi.model.order.Order;
import com.kazi.repositories.InventoryRepository;
import com.kazi.services.ReceiptService;
import com.kazi.services.prices.SimplePricePolicy;

import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    private final static InventoryRepository repository = new InventoryRepository();
    private final static ReceiptService service = new ReceiptService(repository, new SimplePricePolicy());
    private final static OrderInputParser parser = new OrderInputParser();

    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
        } else {
            Order order = parser.parse(args);
            System.out.println(service.generateReceipt(order));
        }
    }

    private static void printHelp() {
        System.out.println("Please provide order details in form of a comma separated ID list.");
        System.out.println("Sample \"1,1,3\", where first ID is an Offering ID and following Ids are OPTIONAL extra");
        System.out.println("Available Offerings:");
        System.out.println(repository.getOfferings().stream().map(Objects::toString).collect(Collectors.joining("\n")));
        System.out.println("Available Extras:");
        System.out.println(repository.getExtras().stream().map(Objects::toString).collect(Collectors.joining("\n")));

    }
}
