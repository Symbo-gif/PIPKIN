package com.pipkin.core.model

/**
 * Append-only care/life event for a save slot. [id] is 0 before Room assigns a row id.
 */
data class HistoryLog(
    val id: Long,
    val slotId: Int,
    val petId: String,
    val eventType: HistoryEventType,
    val payloadJson: String,
    val atMillis: Long,
) {
    init {
        require(SaveSlots.isValid(slotId)) {
            "slotId must be in ${SaveSlots.MIN_ID}..${SaveSlots.MAX_ID}, was $slotId"
        }
        require(atMillis >= 0L) { "atMillis must be >= 0, was $atMillis" }
    }
}
