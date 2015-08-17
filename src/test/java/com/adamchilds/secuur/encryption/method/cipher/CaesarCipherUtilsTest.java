package com.adamchilds.secuur.encryption.method.cipher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link CaesarCipherUtils} class.
 */
public class CaesarCipherUtilsTest {

    private static final int SHIFT = 9;
    private static final int RANDOM_SHIFT = CaesarCipherUtils.generateRandomShift();

    @Test
    public void testEncipher() {
        // Null
        assertEquals("", CaesarCipherUtils.encipher((String) null, SHIFT));
        assertArrayEquals(new byte[0], CaesarCipherUtils.encipher((byte[]) null, SHIFT));

        // Empty
        assertEquals("", CaesarCipherUtils.encipher("", SHIFT));
        assertEquals("    ", CaesarCipherUtils.encipher("    ", SHIFT));
        assertArrayEquals(new byte[0], CaesarCipherUtils.encipher(new byte[0]));

        // Valid
        assertEquals("Cqrb rb j bcarwp cx nwlahyc.", CaesarCipherUtils.encipher("This is a string to encrypt.", SHIFT));
        assertEquals("Cq|b !b jw0cq3a bcarwp cx nwlahy7.", CaesarCipherUtils.encipher("Th|s !s an0th3r string to encryp7.", SHIFT));
    }

    @Test
    public void testEncipher_randomShift() {
        // Null
        assertEquals("", CaesarCipherUtils.encipher((String) null, RANDOM_SHIFT));
        assertArrayEquals(new byte[0], CaesarCipherUtils.encipher((byte[]) null, RANDOM_SHIFT));

        // Empty
        assertEquals("", CaesarCipherUtils.encipher("", RANDOM_SHIFT));
        assertEquals("    ", CaesarCipherUtils.encipher("    ", RANDOM_SHIFT));
        assertArrayEquals(new byte[0], CaesarCipherUtils.encipher(new byte[0], RANDOM_SHIFT));

        // Valid
        String stringToEncipher = "This is a test.";
        String encipheredString = CaesarCipherUtils.encipher(stringToEncipher, RANDOM_SHIFT);
        assertEquals(stringToEncipher, CaesarCipherUtils.decipher(encipheredString, RANDOM_SHIFT));
    }

    @Test
    public void testDecipher() {
        // Null
        assertEquals("", CaesarCipherUtils.decipher((String) null, SHIFT));
        assertArrayEquals(new byte[0], CaesarCipherUtils.decipher((byte[]) null, SHIFT));

        // Empty
        assertEquals("", CaesarCipherUtils.decipher("", SHIFT));
        assertEquals("    ", CaesarCipherUtils.decipher("    ", SHIFT));
        assertArrayEquals(new byte[0], CaesarCipherUtils.decipher(new byte[0], SHIFT));

        // Valid
        assertEquals("This is a string to decipher.", CaesarCipherUtils.decipher("Cqrb rb j bcarwp cx mnlryqna.", SHIFT));
        assertEquals("Th|s !s an0th3r string to deciphe7.", CaesarCipherUtils.decipher("Cq|b !b jw0cq3a bcarwp cx mnlryqn7.", SHIFT));
    }

    @Test
    public void testDecipher_randomShift() {
        // Null
        assertEquals("", CaesarCipherUtils.decipher((String) null, RANDOM_SHIFT));
        assertArrayEquals(new byte[0], CaesarCipherUtils.decipher((byte[]) null, RANDOM_SHIFT));

        // Empty
        assertEquals("", CaesarCipherUtils.decipher("", RANDOM_SHIFT));
        assertEquals("    ", CaesarCipherUtils.decipher("    ", RANDOM_SHIFT));
        assertArrayEquals(new byte[0], CaesarCipherUtils.decipher(new byte[0], RANDOM_SHIFT));

        // Valid
        String stringToDecipher = "This is a test.";
        String decipheredString = CaesarCipherUtils.decipher(stringToDecipher, RANDOM_SHIFT);
        assertEquals(stringToDecipher, CaesarCipherUtils.encipher(decipheredString, RANDOM_SHIFT));
    }

    @Test
    public void testGenerateRandomShift() {
        assertNotNull(CaesarCipherUtils.generateRandomShift());

        /*
         * This doesn't actually prove that the shift will always be greater than 0, but this gives me enough
         * confidence that it will be. There are 26 total possibilities. The method is set up to always add a constant
         * value of 1 to the random result; therefore, this test should be sufficient.
         */
        for (int i = 0; i < 1000; i++) {
            assertTrue(CaesarCipherUtils.generateRandomShift() > 0);
        }
    }

}