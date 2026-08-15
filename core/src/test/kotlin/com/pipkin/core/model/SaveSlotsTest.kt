package com.pipkin.core.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SaveSlotsTest {
    @Test
    fun save_slots_are_exactly_three_ids_zero_through_two() {
        assertEquals(3, SaveSlots.COUNT)
        assertEquals(0, SaveSlots.MIN_ID)
        assertEquals(2, SaveSlots.MAX_ID)
        assertEquals(setOf(0, 1, 2), SaveSlots.IDS)
        assertTrue(SaveSlots.isValid(0))
        assertTrue(SaveSlots.isValid(1))
        assertTrue(SaveSlots.isValid(2))
        assertFalse(SaveSlots.isValid(-1))
        assertFalse(SaveSlots.isValid(3))
    }
}
