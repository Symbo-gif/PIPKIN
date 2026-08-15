package com.pipkin.core.model

/**
 * History event kinds stored with [HistoryLog]. Payload details stay in JSON.
 */
enum class HistoryEventType {
    HATCH,
    CARE_MISTAKE,
    EVOLUTION,
    DEATH,
    REBIRTH,
}
