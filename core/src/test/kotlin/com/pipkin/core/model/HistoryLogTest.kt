package com.pipkin.core.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class HistoryLogTest {
    @Test
    fun history_log_rejects_slot_id_outside_zero_to_two() {
        assertThrows(IllegalArgumentException::class.java) { sampleHistoryLog(slotId = -1) }
        assertThrows(IllegalArgumentException::class.java) { sampleHistoryLog(slotId = 3) }
        assertEquals(0, sampleHistoryLog(slotId = 0).slotId)
    }

    @Test
    fun history_log_rejects_negative_timestamp() {
        assertThrows(IllegalArgumentException::class.java) {
            sampleHistoryLog(atMillis = -1L)
        }
    }

    @Test
    fun history_log_holds_all_specified_fields() {
        val log = sampleHistoryLog(
            id = 42L,
            slotId = 2,
            petId = "pet-9",
            eventType = HistoryEventType.DEATH,
            payloadJson = """{"cause":"health"}""",
            atMillis = FIXTURE_NOW_MILLIS,
        )

        assertEquals(42L, log.id)
        assertEquals(2, log.slotId)
        assertEquals("pet-9", log.petId)
        assertEquals(HistoryEventType.DEATH, log.eventType)
        assertEquals("""{"cause":"health"}""", log.payloadJson)
        assertEquals(FIXTURE_NOW_MILLIS, log.atMillis)
    }
}
