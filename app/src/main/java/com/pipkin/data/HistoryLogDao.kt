package com.pipkin.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryLogDao {
    @Insert
    suspend fun insert(entity: HistoryLogEntity): Long

    @Query("SELECT * FROM history_logs WHERE id = :id")
    suspend fun getById(id: Long): HistoryLogEntity?

    @Query(
        "SELECT * FROM history_logs WHERE slotId = :slotId ORDER BY atMillis ASC, id ASC",
    )
    suspend fun getForSlot(slotId: Int): List<HistoryLogEntity>
}
