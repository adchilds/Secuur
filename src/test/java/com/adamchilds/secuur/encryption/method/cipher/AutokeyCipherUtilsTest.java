package com.adamchilds.secuur.encryption.method.cipher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link AutokeyCipherUtils} class.
 */
public class AutokeyCipherUtilsTest {

    @Test
    public void testEncipher() {
        // Null
        assertEquals("", "");

        // Empty

        // Valid

    }

    @Test
    public void testEncipher_randomAutokey() {

    }

    @Test
    public void testDecipher() {
        // Null

        // Empty

        // Valid

    }

    @Test
    public void testDecipher_randomAutokey() {

    }

    @Test
    public void testGenerateRandomTabulaRecta() {
        assertNotNull(AutokeyCipherUtils.generateRandomTabulaRecta());
    }

}