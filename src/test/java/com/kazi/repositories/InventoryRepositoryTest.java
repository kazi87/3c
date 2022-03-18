package com.kazi.repositories;

import com.kazi.model.Extra;
import com.kazi.model.Offering;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * As this is a simple in memory model i decided to not mock nor implement integration tests. instead i used the "production" DB
 */
class InventoryRepositoryTest {
    private InventoryRepository testObj = new InventoryRepository();

    @Test
    void shouldReturnOfferings() {
        //  given

        //  when
        List<Offering> result = testObj.getOfferings();

        //  then
        assertEquals(5, result.size());
    }

    @Test
    void shouldReturnEmptyIfOfferingDoesNotExist() {
        //  given
        Integer notExistingId = 99;

        //  when
        Optional<Offering> result = testObj.getOffering(notExistingId);

        //  then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenMissingExtras() {
        //  given
        Integer notExistingId = 99;
        Integer existingId = 1;

        //  when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> testObj.getExtras(List.of(notExistingId, existingId)));

        //  then
        assertEquals("Missing Extras!", ex.getMessage());
    }

    @Test
    void shouldReturnExtras() {
        //  given

        //  when
        List<Extra> result = testObj.getExtras();

        //  then
        assertEquals(3, result.size());
    }
}
