package com.pipkin.data

import com.pipkin.core.model.MeterBounds
import com.pipkin.core.model.PetState
import com.pipkin.core.model.SaveSlots

object PetStateMapper {
    private val converters = PipKinTypeConverters()

    fun toEntity(state: PetState): PetStateEntity =
        PetStateEntity(
            slotId = state.slotId,
            petId = state.petId,
            name = state.name,
            hunger = state.hunger,
            happiness = state.happiness,
            energy = state.energy,
            cleanliness = state.cleanliness,
            health = state.health,
            stage = converters.fromEvolutionStage(state.stage),
            branch = converters.fromEvolutionBranch(state.branch),
            careMistakeCount = state.careMistakeCount,
            hungerZeroSinceMillis = state.hungerZeroSinceMillis,
            lastSimulatedAtMillis = state.lastSimulatedAtMillis,
            createdAtMillis = state.createdAtMillis,
            isDead = state.isDead,
        )

    fun toDomain(
        entity: PetStateEntity,
        nowMillis: Long,
    ): LoadResult {
        val reason = findCorruption(entity, nowMillis)
        if (reason != null) {
            return LoadResult.Corrupt(entity.slotId, reason)
        }
        return runCatching {
            LoadResult.Success(
                PetState(
                    petId = entity.petId,
                    slotId = entity.slotId,
                    name = entity.name,
                    hunger = entity.hunger,
                    happiness = entity.happiness,
                    energy = entity.energy,
                    cleanliness = entity.cleanliness,
                    health = entity.health,
                    stage = requireNotNull(converters.toEvolutionStage(entity.stage)),
                    branch = requireNotNull(converters.toEvolutionBranch(entity.branch)),
                    careMistakeCount = entity.careMistakeCount,
                    hungerZeroSinceMillis = entity.hungerZeroSinceMillis,
                    lastSimulatedAtMillis = entity.lastSimulatedAtMillis,
                    createdAtMillis = entity.createdAtMillis,
                    isDead = entity.isDead,
                ),
            )
        }.getOrElse { error ->
            LoadResult.Corrupt(entity.slotId, error.message ?: "invalid pet state")
        }
    }

    private fun findCorruption(
        entity: PetStateEntity,
        nowMillis: Long,
    ): String? =
        invalidMeter(entity)
            ?: invalidSlot(entity.slotId)
            ?: invalidFutureTimestamp(entity, nowMillis)
            ?: unknownStage(entity)
            ?: unknownBranch(entity)

    private fun invalidMeter(entity: PetStateEntity): String? {
        val meters = listOf(
            "hunger" to entity.hunger,
            "happiness" to entity.happiness,
            "energy" to entity.energy,
            "cleanliness" to entity.cleanliness,
            "health" to entity.health,
        )
        val invalid = meters.firstOrNull { (_, value) -> !MeterBounds.isValid(value) }
        return invalid?.let { "${it.first} ${it.second} out of range" }
    }

    private fun invalidSlot(slotId: Int): String? =
        if (SaveSlots.isValid(slotId)) null else "slotId $slotId outside 0..2"

    private fun invalidFutureTimestamp(
        entity: PetStateEntity,
        nowMillis: Long,
    ): String? =
        if (entity.lastSimulatedAtMillis > nowMillis) {
            "lastSimulatedAtMillis ${entity.lastSimulatedAtMillis} is in the future"
        } else {
            null
        }

    private fun unknownStage(entity: PetStateEntity): String? =
        if (converters.toEvolutionStage(entity.stage) == null) {
            "unknown stage ${entity.stage}"
        } else {
            null
        }

    private fun unknownBranch(entity: PetStateEntity): String? =
        if (converters.toEvolutionBranch(entity.branch) == null) {
            "unknown branch ${entity.branch}"
        } else {
            null
        }
}
