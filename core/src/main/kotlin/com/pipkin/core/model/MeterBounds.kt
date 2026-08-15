package com.pipkin.core.model

/**
 * Inclusive integer meter range locked in `docs/build-plan.md` §2.
 */
object MeterBounds {
    const val MIN: Int = 0
    const val MAX: Int = 100

    fun isValid(value: Int): Boolean = value in MIN..MAX
}
