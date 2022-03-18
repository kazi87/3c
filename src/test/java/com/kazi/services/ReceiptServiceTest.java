package com.kazi.services;

import com.kazi.model.Extra;
import com.kazi.model.Offering;
import com.kazi.model.Type;
import com.kazi.model.order.Order;
import com.kazi.model.order.OrderItem;
import com.kazi.repositories.InventoryRepository;
import com.kazi.services.prices.SimplePricePolicy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Naive tests for generated Receipt in form of String...
 */
@ExtendWith(MockitoExtension.class)
class ReceiptServiceTest {

    @Mock
    private InventoryRepository repoMock;

    @InjectMocks
    private ReceiptService testObj;

    @Test
    void shouldGenerateEmptyReceipt() {
        //  given
        //  when
        String result = testObj.generateReceipt(new Order(List.of()), new SimplePricePolicy());
        //  then
        assertTrue(result.contains("Total Price: 0.00"));
        assertTrue(result.contains("Order ID"));
        assertTrue(result.contains("Order Date"));
        assertTrue(result.contains("Order Time"));
    }

    @Test
    void shouldGenerateValidReceipt() {
        //  given
        when(repoMock.getOffering(eq(1))).thenReturn(Optional.of(new Offering(1, "test offering", Type.COFFEE, new BigDecimal("1.00"))));
        when(repoMock.getExtras(eq(List.of(2)))).thenReturn(List.of(new Extra(1, "test extra", Type.COFFEE, new BigDecimal("1.00"))));

        //  when
        String result = testObj.generateReceipt(new Order(List.of(new OrderItem(1, List.of(2)))), new SimplePricePolicy());
        //  then
        assertTrue(result.contains("Total Price: 2.00"));
        assertTrue(result.contains("-> test extra:  1.00 CHF"));
        assertTrue(result.contains("test offering: 1.00 CHF"));
        assertTrue(result.contains("Order ID"));
        assertTrue(result.contains("Order Date"));
        assertTrue(result.contains("Order Time"));
    }

}
