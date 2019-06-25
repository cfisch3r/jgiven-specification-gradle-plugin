package de.agiledojo.jgivenspec.samples

import com.tngtech.jgiven.junit5.JGivenExtension
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(JGivenExtension::class)
class SingleSpecSample {
    @Test
    internal fun `the first Spec` () {
        assertTrue(true)
    }

}