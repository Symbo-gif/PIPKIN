package com.pipkin.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [PetStateEntity::class, HistoryLogEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(PipKinTypeConverters::class)
abstract class PipKinDatabase : RoomDatabase() {
    abstract fun petStateDao(): PetStateDao

    abstract fun historyLogDao(): HistoryLogDao

    companion object {
        const val NAME: String = "pipkin.db"
    }
}
