package com.pipkin.core

import org.junit.Assert.assertEquals
import org.junit.Test

class PipKinCoreTest {
    @Test
    fun core_module_loads() {
        assertEquals("PipKin", PipKinCore.NAME)
    }
}
