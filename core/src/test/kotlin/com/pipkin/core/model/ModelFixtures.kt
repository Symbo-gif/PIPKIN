package com.pipkin.core.model

internal const val FIXTURE_NOW_MILLIS: Long = 1_700_000_000_000L

internal fun samplePetState(
    petId: String = "pet-1",
    slotId: Int = 0,
    name: String = "Pip",
    hunger: Int = 80,
    happiness: Int = 70,
    energy: Int = 60,
    cleanliness: Int = 90,
    health: Int = 100,
    stage: EvolutionStage = EvolutionStage.EGG,
    branch: EvolutionBranch = EvolutionBranch.UNASSIGNED,
    careMistakeCount: Int = 0,
    hungerZeroSinceMillis: Long? = null,
    lastSimulatedAtMillis: Long = FIXTURE_NOW_MILLIS,
    createdAtMillis: Long = FIXTURE_NOW_MILLIS,
    isDead: Boolean = false,
): PetState =
    PetState(
        petId = petId,
        slotId = slotId,
        name = name,
        hunger = hunger,
        happiness = happiness,
        energy = energy,
        cleanliness = cleanliness,
        health = health,
        stage = stage,
        branch = branch,
        careMistakeCount = careMistakeCount,
        hungerZeroSinceMillis = hungerZeroSinceMillis,
        lastSimulatedAtMillis = lastSimulatedAtMillis,
        createdAtMillis = createdAtMillis,
        isDead = isDead,
    )

internal fun sampleHistoryLog(
    id: Long = 1L,
    slotId: Int = 0,
    petId: String = "pet-1",
    eventType: HistoryEventType = HistoryEventType.HATCH,
    payloadJson: String = """{"name":"Pip"}""",
    atMillis: Long = FIXTURE_NOW_MILLIS,
): HistoryLog =
    HistoryLog(
        id = id,
        slotId = slotId,
        petId = petId,
        eventType = eventType,
        payloadJson = payloadJson,
        atMillis = atMillis,
    )
