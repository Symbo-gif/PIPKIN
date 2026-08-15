package com.pipkin

import org.junit.Assert.assertEquals
import org.junit.Test

class AppCompileSdkContractTest {
    @Test
    fun app_compiles_against_sdk_36_per_build_plan() {
        assertEquals(36, BuildConfig.COMPILE_SDK)
    }
}
