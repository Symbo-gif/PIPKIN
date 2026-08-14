package com.pipkin

import com.pipkin.core.PipKinCore
import org.junit.Assert.assertEquals
import org.junit.Test

class AppSmokeTest {
    @Test
    fun app_depends_on_core() {
        assertEquals("PipKin", PipKinCore.NAME)
    }
}
