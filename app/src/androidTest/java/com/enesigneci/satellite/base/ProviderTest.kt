package com.enesigneci.satellite.base

import org.junit.Test
import org.junit.Assert.*

class ProviderTest {

    private val provider = object : Provider<String> {
        override fun get(id: Int): String {
            return "Test String $id"
        }
    }

    @Test
    fun testProviderGet() {
        // Given
        val expectedString = "Test String 1234"

        // When
        val result = provider.get(1234)

        // Then
        assertEquals(expectedString, result)
    }
}
