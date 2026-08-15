package com.pipkin.data

/**
 * Typed load outcome for untrusted Room rows. [Corrupt] is the crash-loop
 * recovery signal; U11 owns the recovery UI.
 */
sealed class LoadResult {
    data class Success(val value: Any) : LoadResult()

    data class Corrupt(
        val slotId: Int,
        val reason: String,
    ) : LoadResult()
}
