package com.pipkin.core.model

/**
 * Three local save slots, ids `0..2`, per `docs/build-plan.md` §3.7.
 */
object SaveSlots {
    const val COUNT: Int = 3
    const val MIN_ID: Int = 0
    const val MAX_ID: Int = 2
    val IDS: Set<Int> = (MIN_ID..MAX_ID).toSet()

    fun isValid(slotId: Int): Boolean = slotId in MIN_ID..MAX_ID
}
