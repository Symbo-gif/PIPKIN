package com.pipkin.data

import com.pipkin.core.model.HistoryEventType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HistoryLogMapperTest {
    @Test
    fun mapper_round_trips_history_log_fields_to_entity_and_back() {
        val original = sampleHistoryLog(
            id = 9L,
            slotId = 2,
            petId = "pet-hist",
            eventType = HistoryEventType.CARE_MISTAKE,
            payloadJson = """{"minutes":20}""",
            atMillis = FIXTURE_NOW_MILLIS,
        )

        val entity = HistoryLogMapper.toEntity(original)
        val loaded = HistoryLogMapper.toDomain(entity)

        assertTrue(loaded is LoadResult.Success)
        assertEquals(original, (loaded as LoadResult.Success).value)
    }

    @Test
    fun mapper_rejects_history_entity_with_slot_id_outside_zero_to_two() {
        val result = HistoryLogMapper.toDomain(sampleHistoryLogEntity(slotId = 4))
        assertTrue(result is LoadResult.Corrupt)
        assertEquals(4, (result as LoadResult.Corrupt).slotId)
    }

    @Test
    fun mapper_rejects_history_entity_with_unknown_event_type() {
        val result = HistoryLogMapper.toDomain(sampleHistoryLogEntity(eventType = "TELEPORT"))
        assertTrue(result is LoadResult.Corrupt)
    }

    @Test
    fun mapper_rejects_history_entity_with_negative_timestamp() {
        val result = HistoryLogMapper.toDomain(sampleHistoryLogEntity(atMillis = -1L))
        assertTrue(result is LoadResult.Corrupt)
    }
}
