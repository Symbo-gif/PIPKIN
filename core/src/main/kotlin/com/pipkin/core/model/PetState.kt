package com.pipkin.core.model

/**
 * Domain pet snapshot. `:app` Room maps this to `PetStateEntity`.
 * Simulation math lives in later units, not here.
 */
data class PetState(
    val petId: String,
    val slotId: Int,
    val name: String,
    val hunger: Int,
    val happiness: Int,
    val energy: Int,
    val cleanliness: Int,
    val health: Int,
    val stage: EvolutionStage,
    val branch: EvolutionBranch,
    val careMistakeCount: Int,
    val hungerZeroSinceMillis: Long?,
    val lastSimulatedAtMillis: Long,
    val createdAtMillis: Long,
    val isDead: Boolean,
) {
    init {
        require(SaveSlots.isValid(slotId)) {
            "slotId must be in ${SaveSlots.MIN_ID}..${SaveSlots.MAX_ID}, was $slotId"
        }
        require(MeterBounds.isValid(hunger)) { "hunger out of range: $hunger" }
        require(MeterBounds.isValid(happiness)) { "happiness out of range: $happiness" }
        require(MeterBounds.isValid(energy)) { "energy out of range: $energy" }
        require(MeterBounds.isValid(cleanliness)) { "cleanliness out of range: $cleanliness" }
        require(MeterBounds.isValid(health)) { "health out of range: $health" }
        require(careMistakeCount >= 0) { "careMistakeCount must be >= 0, was $careMistakeCount" }
        require(lastSimulatedAtMillis >= 0L) {
            "lastSimulatedAtMillis must be >= 0, was $lastSimulatedAtMillis"
        }
        require(createdAtMillis >= 0L) { "createdAtMillis must be >= 0, was $createdAtMillis" }
        hungerZeroSinceMillis?.let { zeroSince ->
            require(zeroSince >= 0L) { "hungerZeroSinceMillis must be >= 0, was $zeroSince" }
        }
    }
}
