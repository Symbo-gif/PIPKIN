package com.pipkin.data

import androidx.room.TypeConverter
import com.pipkin.core.model.EvolutionBranch
import com.pipkin.core.model.EvolutionStage
import com.pipkin.core.model.HistoryEventType

/**
 * String ↔ enum converters. Entities persist enum names as TEXT so a corrupt
 * name can be mapped to [LoadResult.Corrupt] instead of crashing Room.
 */
class PipKinTypeConverters {
    @TypeConverter
    fun fromEvolutionStage(value: EvolutionStage): String = value.name

    @TypeConverter
    fun toEvolutionStage(value: String): EvolutionStage? =
        EvolutionStage.entries.find { it.name == value }

    @TypeConverter
    fun fromEvolutionBranch(value: EvolutionBranch): String = value.name

    @TypeConverter
    fun toEvolutionBranch(value: String): EvolutionBranch? =
        EvolutionBranch.entries.find { it.name == value }

    @TypeConverter
    fun fromHistoryEventType(value: HistoryEventType): String = value.name

    @TypeConverter
    fun toHistoryEventType(value: String): HistoryEventType? =
        HistoryEventType.entries.find { it.name == value }
}
