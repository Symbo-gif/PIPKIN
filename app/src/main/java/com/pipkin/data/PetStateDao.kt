package com.pipkin.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PetStateDao {
    @Query("SELECT * FROM pet_states WHERE slotId = :slotId LIMIT 1")
    suspend fun getBySlot(slotId: Int): PetStateEntity?

    @Query("SELECT * FROM pet_states")
    suspend fun getAll(): List<PetStateEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: PetStateEntity)

    @Query("DELETE FROM pet_states WHERE slotId = :slotId")
    suspend fun deleteBySlot(slotId: Int)
}
