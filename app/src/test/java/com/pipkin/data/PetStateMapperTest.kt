package com.pipkin.data

import com.pipkin.core.model.EvolutionBranch
import com.pipkin.core.model.EvolutionStage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PetStateMapperTest {
    @Test
    fun mapper_round_trips_pet_state_fields_to_entity_and_back() {
        val original = samplePetState(
            petId = "round-trip",
            slotId = 1,
            name = "Kin",
            hunger = 11,
            happiness = 22,
            energy = 33,
            cleanliness = 44,
            health = 55,
            stage = EvolutionStage.TEEN,
            branch = EvolutionBranch.WILD,
            careMistakeCount = 3,
            hungerZeroSinceMillis = FIXTURE_NOW_MILLIS - 1,
            lastSimulatedAtMillis = FIXTURE_NOW_MILLIS,
            createdAtMillis = FIXTURE_NOW_MILLIS - 10,
            isDead = false,
        )

        val entity = PetStateMapper.toEntity(original)
        val loaded = PetStateMapper.toDomain(entity, nowMillis = FIXTURE_NOW_MILLIS)

        assertTrue(loaded is LoadResult.Success)
        assertEquals(original, (loaded as LoadResult.Success).value)
    }

    @Test
    fun mapper_rejects_entity_with_hunger_out_of_range() {
        val tooLow = PetStateMapper.toDomain(
            samplePetStateEntity(hunger = -1),
            nowMillis = FIXTURE_NOW_MILLIS,
        )
        val tooHigh = PetStateMapper.toDomain(
            samplePetStateEntity(hunger = 101),
            nowMillis = FIXTURE_NOW_MILLIS,
        )

        assertTrue(tooLow is LoadResult.Corrupt)
        assertTrue(tooHigh is LoadResult.Corrupt)
        assertEquals(0, (tooLow as LoadResult.Corrupt).slotId)
        assertEquals(0, (tooHigh as LoadResult.Corrupt).slotId)
    }

    @Test
    fun mapper_rejects_entity_with_any_meter_out_of_range() {
        val entities = listOf(
            samplePetStateEntity(happiness = -1),
            samplePetStateEntity(energy = 101),
            samplePetStateEntity(cleanliness = -5),
            samplePetStateEntity(health = 150),
        )
        for (entity in entities) {
            val result = PetStateMapper.toDomain(entity, nowMillis = FIXTURE_NOW_MILLIS)
            assertTrue("expected corrupt for $entity", result is LoadResult.Corrupt)
        }
    }

    @Test
    fun mapper_rejects_entity_with_future_last_simulated_timestamp() {
        val entity = samplePetStateEntity(lastSimulatedAtMillis = FIXTURE_NOW_MILLIS + 1)
        val result = PetStateMapper.toDomain(entity, nowMillis = FIXTURE_NOW_MILLIS)

        assertTrue(result is LoadResult.Corrupt)
        assertEquals(0, (result as LoadResult.Corrupt).slotId)
    }

    @Test
    fun mapper_rejects_entity_with_negative_created_at_timestamp() {
        val result = PetStateMapper.toDomain(
            samplePetStateEntity(createdAtMillis = -1L),
            nowMillis = FIXTURE_NOW_MILLIS,
        )
        assertTrue(result is LoadResult.Corrupt)
    }

    @Test
    fun mapper_rejects_entity_with_unknown_stage_value() {
        val result = PetStateMapper.toDomain(
            samplePetStateEntity(stage = "DRAGON"),
            nowMillis = FIXTURE_NOW_MILLIS,
        )
        assertTrue(result is LoadResult.Corrupt)
    }

    @Test
    fun mapper_rejects_entity_with_unknown_branch_value() {
        val result = PetStateMapper.toDomain(
            samplePetStateEntity(branch = "CRYSTAL"),
            nowMillis = FIXTURE_NOW_MILLIS,
        )
        assertTrue(result is LoadResult.Corrupt)
    }

    @Test
    fun mapper_rejects_entity_with_slot_id_outside_zero_to_two() {
        val result = PetStateMapper.toDomain(
            samplePetStateEntity(slotId = 3),
            nowMillis = FIXTURE_NOW_MILLIS,
        )
        assertTrue(result is LoadResult.Corrupt)
        assertEquals(3, (result as LoadResult.Corrupt).slotId)
    }

    @Test
    fun mapper_accepts_last_simulated_timestamp_equal_to_now() {
        val entity = samplePetStateEntity(lastSimulatedAtMillis = FIXTURE_NOW_MILLIS)
        val result = PetStateMapper.toDomain(entity, nowMillis = FIXTURE_NOW_MILLIS)
        assertTrue(result is LoadResult.Success)
    }
}
