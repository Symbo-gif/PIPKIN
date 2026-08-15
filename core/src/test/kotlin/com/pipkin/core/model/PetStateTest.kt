package com.pipkin.core.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Test

class PetStateTest {
    @Test
    fun pet_state_accepts_meters_at_zero_and_one_hundred_inclusive() {
        val floor = samplePetState(
            hunger = MeterBounds.MIN,
            happiness = MeterBounds.MIN,
            energy = MeterBounds.MIN,
            cleanliness = MeterBounds.MIN,
            health = MeterBounds.MIN,
        )
        val ceiling = samplePetState(
            hunger = MeterBounds.MAX,
            happiness = MeterBounds.MAX,
            energy = MeterBounds.MAX,
            cleanliness = MeterBounds.MAX,
            health = MeterBounds.MAX,
        )

        assertEquals(0, floor.hunger)
        assertEquals(0, floor.health)
        assertEquals(100, ceiling.hunger)
        assertEquals(100, ceiling.health)
    }

    @Test
    fun pet_state_rejects_any_meter_outside_zero_to_one_hundred() {
        val outOfRange = listOf(-1, 101)
        val fields = listOf("hunger", "happiness", "energy", "cleanliness", "health")
        for (value in outOfRange) {
            for (field in fields) {
                assertThrows(field + "=" + value, IllegalArgumentException::class.java) {
                    when (field) {
                        "hunger" -> samplePetState(hunger = value)
                        "happiness" -> samplePetState(happiness = value)
                        "energy" -> samplePetState(energy = value)
                        "cleanliness" -> samplePetState(cleanliness = value)
                        "health" -> samplePetState(health = value)
                        else -> error("unexpected field")
                    }
                }
            }
        }
    }

    @Test
    fun pet_state_rejects_slot_id_outside_zero_to_two() {
        assertThrows(IllegalArgumentException::class.java) { samplePetState(slotId = -1) }
        assertThrows(IllegalArgumentException::class.java) { samplePetState(slotId = 3) }
        assertEquals(2, samplePetState(slotId = 2).slotId)
    }

    @Test
    fun pet_state_rejects_negative_timestamps() {
        assertThrows(IllegalArgumentException::class.java) {
            samplePetState(lastSimulatedAtMillis = -1L)
        }
        assertThrows(IllegalArgumentException::class.java) {
            samplePetState(createdAtMillis = -1L)
        }
        assertThrows(IllegalArgumentException::class.java) {
            samplePetState(hungerZeroSinceMillis = -1L)
        }
    }

    @Test
    fun pet_state_allows_null_hunger_zero_since() {
        val state = samplePetState(hungerZeroSinceMillis = null)
        assertNull(state.hungerZeroSinceMillis)
    }

    @Test
    fun pet_state_rejects_negative_care_mistake_count() {
        assertThrows(IllegalArgumentException::class.java) {
            samplePetState(careMistakeCount = -1)
        }
    }

    @Test
    fun pet_state_holds_all_specified_fields() {
        val state = samplePetState(
            petId = "abc",
            slotId = 1,
            name = "Kin",
            hunger = 10,
            happiness = 20,
            energy = 30,
            cleanliness = 40,
            health = 50,
            stage = EvolutionStage.CHILD,
            branch = EvolutionBranch.KINDRED,
            careMistakeCount = 2,
            hungerZeroSinceMillis = FIXTURE_NOW_MILLIS,
            lastSimulatedAtMillis = FIXTURE_NOW_MILLIS + 1,
            createdAtMillis = FIXTURE_NOW_MILLIS,
            isDead = true,
        )

        assertEquals("abc", state.petId)
        assertEquals(1, state.slotId)
        assertEquals("Kin", state.name)
        assertEquals(10, state.hunger)
        assertEquals(20, state.happiness)
        assertEquals(30, state.energy)
        assertEquals(40, state.cleanliness)
        assertEquals(50, state.health)
        assertEquals(EvolutionStage.CHILD, state.stage)
        assertEquals(EvolutionBranch.KINDRED, state.branch)
        assertEquals(2, state.careMistakeCount)
        assertEquals(FIXTURE_NOW_MILLIS, state.hungerZeroSinceMillis)
        assertEquals(FIXTURE_NOW_MILLIS + 1, state.lastSimulatedAtMillis)
        assertEquals(FIXTURE_NOW_MILLIS, state.createdAtMillis)
        assertEquals(true, state.isDead)
    }
}
