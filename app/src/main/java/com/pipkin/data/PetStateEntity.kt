package com.pipkin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pet_states")
data class PetStateEntity(
    @PrimaryKey val slotId: Int,
    val petId: String,
    val name: String,
    val hunger: Int,
    val happiness: Int,
    val energy: Int,
    val cleanliness: Int,
    val health: Int,
    val stage: String,
    val branch: String,
    val careMistakeCount: Int,
    val hungerZeroSinceMillis: Long?,
    val lastSimulatedAtMillis: Long,
    val createdAtMillis: Long,
    val isDead: Boolean,
)
