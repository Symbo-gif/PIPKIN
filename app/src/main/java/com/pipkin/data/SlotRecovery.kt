package com.pipkin.data

class SlotRecovery(
    private val petStateDao: PetStateDao,
) {
    suspend fun clearSlot(slotId: Int) {
        petStateDao.deleteBySlot(slotId)
    }
}
