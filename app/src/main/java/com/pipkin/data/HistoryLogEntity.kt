package com.pipkin.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "history_logs",
    indices = [
        Index(value = ["slotId"]),
        Index(value = ["petId"]),
    ],
)
data class HistoryLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val slotId: Int,
    val petId: String,
    val eventType: String,
    val payloadJson: String,
    val atMillis: Long,
)
