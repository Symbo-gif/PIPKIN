package com.pipkin.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [26])
class PipKinDatabaseDaoTest {
    private lateinit var database: PipKinDatabase
    private lateinit var petStateDao: PetStateDao
    private lateinit var historyLogDao: HistoryLogDao

    @Before
    fun openInMemoryDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PipKinDatabase::class.java,
        ).allowMainThreadQueries().build()
        petStateDao = database.petStateDao()
        historyLogDao = database.historyLogDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun pet_state_dao_round_trips_insert_and_read() = runTest {
        val entity = samplePetStateEntity(slotId = 0, name = "Pip")

        petStateDao.upsert(entity)
        val loaded = petStateDao.getBySlot(0)

        assertEquals(entity, loaded)
    }

    @Test
    fun pet_state_dao_updates_existing_slot_in_place() = runTest {
        petStateDao.upsert(samplePetStateEntity(slotId = 0, name = "First", hunger = 80))
        petStateDao.upsert(samplePetStateEntity(slotId = 0, name = "Second", hunger = 40))

        val loaded = petStateDao.getBySlot(0)
        assertNotNull(loaded)
        assertEquals("Second", loaded?.name)
        assertEquals(40, loaded?.hunger)
        assertEquals(1, petStateDao.getAll().size)
    }

    @Test
    fun history_log_dao_round_trips_insert_and_read() = runTest {
        val id = historyLogDao.insert(sampleHistoryLogEntity(id = 0L, payloadJson = """{"ok":true}"""))
        val loaded = historyLogDao.getById(id)

        assertNotNull(loaded)
        assertEquals("""{"ok":true}""", loaded?.payloadJson)
        assertEquals(0, loaded?.slotId)
    }

    @Test
    fun writing_slot_one_does_not_change_slot_zero() = runTest {
        petStateDao.upsert(samplePetStateEntity(slotId = 0, petId = "a", name = "Alpha", hunger = 90))
        petStateDao.upsert(samplePetStateEntity(slotId = 1, petId = "b", name = "Beta", hunger = 10))
        petStateDao.upsert(samplePetStateEntity(slotId = 1, petId = "b", name = "Beta-2", hunger = 5))

        val slotZero = petStateDao.getBySlot(0)
        val slotOne = petStateDao.getBySlot(1)

        assertEquals("Alpha", slotZero?.name)
        assertEquals(90, slotZero?.hunger)
        assertEquals("a", slotZero?.petId)
        assertEquals("Beta-2", slotOne?.name)
        assertEquals(5, slotOne?.hunger)
        assertEquals(2, petStateDao.getAll().size)
    }

    @Test
    fun history_log_for_slot_one_does_not_include_slot_zero_events() = runTest {
        historyLogDao.insert(sampleHistoryLogEntity(slotId = 0, petId = "a", payloadJson = "zero"))
        historyLogDao.insert(sampleHistoryLogEntity(slotId = 1, petId = "b", payloadJson = "one"))

        val slotZero = historyLogDao.getForSlot(0)
        val slotOne = historyLogDao.getForSlot(1)

        assertEquals(1, slotZero.size)
        assertEquals("zero", slotZero[0].payloadJson)
        assertEquals(1, slotOne.size)
        assertEquals("one", slotOne[0].payloadJson)
    }

    @Test
    fun empty_slot_returns_no_pet_state() = runTest {
        petStateDao.upsert(samplePetStateEntity(slotId = 0))
        assertNull(petStateDao.getBySlot(1))
        assertNull(petStateDao.getBySlot(2))
    }

    @Test
    fun slot_recovery_deletes_corrupt_slot_without_affecting_other_slots() = runTest {
        petStateDao.upsert(samplePetStateEntity(slotId = 0, name = "Keep"))
        petStateDao.upsert(samplePetStateEntity(slotId = 1, name = "Drop"))
        val recovery = SlotRecovery(petStateDao)

        recovery.clearSlot(1)

        assertEquals("Keep", petStateDao.getBySlot(0)?.name)
        assertNull(petStateDao.getBySlot(1))
        assertEquals(1, petStateDao.getAll().size)
    }

    @Test
    fun history_logs_for_a_slot_are_ordered_by_time_then_id() = runTest {
        val later = historyLogDao.insert(
            sampleHistoryLogEntity(slotId = 0, atMillis = FIXTURE_NOW_MILLIS + 10, payloadJson = "later"),
        )
        val earlier = historyLogDao.insert(
            sampleHistoryLogEntity(slotId = 0, atMillis = FIXTURE_NOW_MILLIS, payloadJson = "earlier"),
        )
        val sameTime = historyLogDao.insert(
            sampleHistoryLogEntity(slotId = 0, atMillis = FIXTURE_NOW_MILLIS, payloadJson = "same-time"),
        )

        val logs = historyLogDao.getForSlot(0)
        assertEquals(listOf("earlier", "same-time", "later"), logs.map { it.payloadJson })
        assertTrue(earlier < sameTime)
        assertTrue(later > 0)
    }
}
