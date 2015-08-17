package com.adamchilds.secuur.encryption.method.cipher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link SubstitutionCipherUtils} class.
 */
public class SubstitutionCipherUtilsTest {

    private static final char[] RANDOM_SUBSTITUTIONS = SubstitutionCipherUtils.generateRandomSubstitutions();

    private static final char[] SUBSTITUTIONS = {
            // ASCII
            'H', 'z', '@', 'd', '0', 'f', '8', 'h', 'o', '*', 'L', 'l', 'm', 'V', 'i', '?', '9', 'r', '|', '.', 'u', 'v', 'w', 'x', '6', 'b',
            '1', ']', 'C', '[', 'E', ';', 'G', 'a', '&', 'J', '!', 'k', '<', 'N', '\'', 'P', 'Q', '=', 'S', '\\', 'U', '-', 'W', '+', 'Y', 'Z',

            // Special Characters
            '2', '~', 'K', 'c', 'g', '3', '%', '7', 'I', 'j', '(', ')', 'n', '_', 'R', 'X',
            'D', 'B', '{', '}', 'F', '5', ',', 't', 'M', '>', '/', 'p', 'O', '"', 'T', 's', ' ',

            // Numbers
            'e', 'A', '`', '$', '4', ':', 'y', '^', '#', 'q'
    };

    @Test
    public void testEncipher() {
        // Null
        assertEquals("", SubstitutionCipherUtils.encipher((String) null, SUBSTITUTIONS));
        assertArrayEquals(new byte[0], SubstitutionCipherUtils.encipher((byte[]) null, SUBSTITUTIONS));

        // Empty
        assertEquals("", SubstitutionCipherUtils.encipher("", SUBSTITUTIONS));
        assertArrayEquals(new byte[0], SubstitutionCipherUtils.encipher(new byte[0]));

        // Whitespace
        String unencryptedString = "    ";
        assertEquals(unencryptedString, SubstitutionCipherUtils.encipher(unencryptedString, SUBSTITUTIONS));

        // Valid
        unencryptedString = "This is an un-encrypted String.";
        assertEquals("\\ho| o| HV uVn0V@r6?.0d S.roV8t", SubstitutionCipherUtils.encipher(unencryptedString, SUBSTITUTIONS));

        unencryptedString = "This is an0th3R un_encrypted, Str1N&...";
        assertEquals("\\ho| o| HVe.h$= uV_0V@r6?.0d, S.rANIttt", SubstitutionCipherUtils.encipher(unencryptedString, SUBSTITUTIONS));

        unencryptedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 `~!@#$%^&*()-_=+[]{};:'\",<.>/?\\|";
        String expected = "Hz@d0f8ho*LlmVi?9r|.uvwx6b1]C[E;Ga&J!k<N'PQ=S\\U-W+YZeA`$4:y^#q 2~Kcg3%7Ij()n_RXDB{}F5O\",Mt>/pTs";
        assertEquals(expected, SubstitutionCipherUtils.encipher(unencryptedString, SUBSTITUTIONS));

        unencryptedString = "This is a bad example (just kidding).";
        assertEquals("This is K mKd exKmple (just kidding).", SubstitutionCipherUtils.encipher(unencryptedString, new char[] { 'K', 'm' }));
    }

    @Test
    public void testEncipher_randomSubstitutions() {
        // Null
        String encryptedString = SubstitutionCipherUtils.encipher((String) null, RANDOM_SUBSTITUTIONS);
        assertEquals("", SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Empty
        String unencryptedString = "";
        encryptedString = SubstitutionCipherUtils.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Whitespace
        unencryptedString = "    ";
        encryptedString = SubstitutionCipherUtils.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Valid
        unencryptedString = "This is an un-encrypted String.";
        encryptedString = SubstitutionCipherUtils.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        unencryptedString = "This is an0th3R un_encrypted, Str1N&...";
        encryptedString = SubstitutionCipherUtils.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        unencryptedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 `~!@#$%^&*()-_=+[]{};:'\",<.>/?\\|";
        encryptedString = SubstitutionCipherUtils.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));
    }

    @Test
    public void testDecipher() {
        // Null
        assertEquals("", SubstitutionCipherUtils.decipher((String) null, SUBSTITUTIONS));
        assertArrayEquals(new byte[0], SubstitutionCipherUtils.decipher((byte[]) null, SUBSTITUTIONS));

        // Empty
        assertEquals("", SubstitutionCipherUtils.decipher("", SUBSTITUTIONS));
        assertArrayEquals(new byte[0], SubstitutionCipherUtils.decipher(new byte[0], SUBSTITUTIONS));

        // Whitespace
        String encryptedString = "    ";
        assertEquals(encryptedString, SubstitutionCipherUtils.decipher(encryptedString, SUBSTITUTIONS));

        // Valid
        encryptedString = "\\ho| o| HV uVn0V@r6?.0d S.roV8t";
        assertEquals("This is an un-encrypted String.", SubstitutionCipherUtils.decipher(encryptedString, SUBSTITUTIONS));

        encryptedString = "\\ho| o| HVe.h$= uV_0V@r6?.0d, S.rANIttt";
        assertEquals("This is an0th3R un_encrypted, Str1N&...", SubstitutionCipherUtils.decipher(encryptedString, SUBSTITUTIONS));

        encryptedString = "Hz@d0f8ho*LlmVi?9r|.uvwx6b1]C[E;Ga&J!k<N'PQ=S\\U-W+YZeA`$4:y^#q 2~Kcg3%7Ij()n_RXDB{}F5O\",Mt>/pTs";
        String expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 `~!@#$%^&*()-_=+[]{};:'\",<.>/?\\|";
        assertEquals(expected, SubstitutionCipherUtils.decipher(encryptedString, SUBSTITUTIONS));

        encryptedString = "This is K mKd exKmple (just kidding).";
        assertEquals("This is a bad exabple (just kidding).", SubstitutionCipherUtils.decipher(encryptedString, new char[] { 'K', 'm' }));
    }

    @Test
    public void testDecipher_randomSubstitutions() {
        // Null
        String encryptedString = SubstitutionCipherUtils.encipher((String) null, RANDOM_SUBSTITUTIONS);
        assertEquals("", SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Empty
        String unencryptedString = "";
        encryptedString = SubstitutionCipherUtils.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Whitespace
        unencryptedString = "    ";
        encryptedString = SubstitutionCipherUtils.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Valid
        unencryptedString = "This is an un-encrypted String.";
        encryptedString = SubstitutionCipherUtils.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        unencryptedString = "This is an0th3R un_encrypted, Str1N&...";
        encryptedString = SubstitutionCipherUtils.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        unencryptedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 `~!@#$%^&*()-_=+[]{};:'\",<.>/?\\|";
        encryptedString = SubstitutionCipherUtils.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipherUtils.decipher(encryptedString, RANDOM_SUBSTITUTIONS));
    }

    @Test
    public void testGenerateRandomSubstitutions() {
        char[] subs = SubstitutionCipherUtils.generateRandomSubstitutions();

        assertNotNull(subs);
        assertEquals(95, subs.length);
    }

}