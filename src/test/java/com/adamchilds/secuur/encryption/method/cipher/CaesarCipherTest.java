package com.adamchilds.secuur.encryption.method.cipher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link CaesarCipher} class.
 */
public class CaesarCipherTest {

    private static final int SHIFT = 9;
    private static final int RANDOM_SHIFT = CaesarCipher.generateRandomShift();

    @Test
    public void testEncipher() {
        // Null
        assertEquals("", CaesarCipher.encipher((String) null, SHIFT));
        assertArrayEquals(new byte[0], CaesarCipher.encipher((byte[]) null, SHIFT));

        // Empty
        assertEquals("", CaesarCipher.encipher("", SHIFT));
        assertEquals("    ", CaesarCipher.encipher("    ", SHIFT));
        assertArrayEquals(new byte[0], CaesarCipher.encipher(new byte[0]));

        // Valid
        assertEquals("Cqrb rb j bcarwp cx nwlahyc.", CaesarCipher.encipher("This is a string to encrypt.", SHIFT));
        assertEquals("Cq|b !b jw0cq3a bcarwp cx nwlahy7.", CaesarCipher.encipher("Th|s !s an0th3r string to encryp7.", SHIFT));
    }

    @Test
    public void testEncipher_randomShift() {
        // Null
        assertEquals("", CaesarCipher.encipher((String) null, RANDOM_SHIFT));
        assertArrayEquals(new byte[0], CaesarCipher.encipher((byte[]) null, RANDOM_SHIFT));

        // Empty
        assertEquals("", CaesarCipher.encipher("", RANDOM_SHIFT));
        assertEquals("    ", CaesarCipher.encipher("    ", RANDOM_SHIFT));
        assertArrayEquals(new byte[0], CaesarCipher.encipher(new byte[0], RANDOM_SHIFT));

        // Valid
        String stringToEncipher = "This is a test.";
        String encipheredString = CaesarCipher.encipher(stringToEncipher, RANDOM_SHIFT);
        assertEquals(stringToEncipher, CaesarCipher.decipher(encipheredString, RANDOM_SHIFT));
    }

    @Test
    public void testDecipher() {
        // Null
        assertEquals("", CaesarCipher.decipher((String) null, SHIFT));
        assertArrayEquals(new byte[0], CaesarCipher.decipher((byte[]) null, SHIFT));

        // Empty
        assertEquals("", CaesarCipher.decipher("", SHIFT));
        assertEquals("    ", CaesarCipher.decipher("    ", SHIFT));
        assertArrayEquals(new byte[0], CaesarCipher.decipher(new byte[0], SHIFT));

        // Valid
        assertEquals("This is a string to decipher.", CaesarCipher.decipher("Cqrb rb j bcarwp cx mnlryqna.", SHIFT));
        assertEquals("Th|s !s an0th3r string to deciphe7.", CaesarCipher.decipher("Cq|b !b jw0cq3a bcarwp cx mnlryqn7.", SHIFT));
    }

    @Test
    public void testDecipher_randomShift() {
        // Null
        assertEquals("", CaesarCipher.decipher((String) null, RANDOM_SHIFT));
        assertArrayEquals(new byte[0], CaesarCipher.decipher((byte[]) null, RANDOM_SHIFT));

        // Empty
        assertEquals("", CaesarCipher.decipher("", RANDOM_SHIFT));
        assertEquals("    ", CaesarCipher.decipher("    ", RANDOM_SHIFT));
        assertArrayEquals(new byte[0], CaesarCipher.decipher(new byte[0], RANDOM_SHIFT));

        // Valid
        String stringToDecipher = "This is a test.";
        String decipheredString = CaesarCipher.decipher(stringToDecipher, RANDOM_SHIFT);
        assertEquals(stringToDecipher, CaesarCipher.encipher(decipheredString, RANDOM_SHIFT));
    }

    @Test
    public void testGenerateRandomShift() {
        assertNotNull(CaesarCipher.generateRandomShift());

        /*
         * This doesn't actually prove that the shift will always be greater than 0, but this gives me enough
         * confidence that it will be. There are 26 total possibilities. The method is set up to always add a constant
         * value of 1 to the random result; therefore, this test should be sufficient.
         */
        for (int i = 0; i < 1000; i++) {
            assertTrue(CaesarCipher.generateRandomShift() > 0);
        }
    }

}