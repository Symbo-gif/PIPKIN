package com.pipkin.data

import com.pipkin.core.model.HistoryLog
import com.pipkin.core.model.SaveSlots

object HistoryLogMapper {
    private val converters = PipKinTypeConverters()

    fun toEntity(log: HistoryLog): HistoryLogEntity =
        HistoryLogEntity(
            id = log.id,
            slotId = log.slotId,
            petId = log.petId,
            eventType = converters.fromHistoryEventType(log.eventType),
            payloadJson = log.payloadJson,
            atMillis = log.atMillis,
        )

    fun toDomain(entity: HistoryLogEntity): LoadResult {
        val reason = findCorruption(entity)
        if (reason != null) {
            return LoadResult.Corrupt(entity.slotId, reason)
        }
        return runCatching {
            LoadResult.Success(
                HistoryLog(
                    id = entity.id,
                    slotId = entity.slotId,
                    petId = entity.petId,
                    eventType = requireNotNull(converters.toHistoryEventType(entity.eventType)),
                    payloadJson = entity.payloadJson,
                    atMillis = entity.atMillis,
                ),
            )
        }.getOrElse { error ->
            LoadResult.Corrupt(entity.slotId, error.message ?: "invalid history log")
        }
    }

    private fun findCorruption(entity: HistoryLogEntity): String? =
        invalidSlot(entity.slotId) ?: unknownEventType(entity)

    private fun invalidSlot(slotId: Int): String? =
        if (SaveSlots.isValid(slotId)) null else "slotId $slotId outside 0..2"

    private fun unknownEventType(entity: HistoryLogEntity): String? =
        if (converters.toHistoryEventType(entity.eventType) == null) {
            "unknown event ${entity.eventType}"
        } else {
            null
        }
}
