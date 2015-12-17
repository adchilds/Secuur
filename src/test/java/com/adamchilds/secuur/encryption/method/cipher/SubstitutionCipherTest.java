package com.adamchilds.secuur.encryption.method.cipher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link SubstitutionCipher} class.
 */
public class SubstitutionCipherTest {

    private static final char[] RANDOM_SUBSTITUTIONS = SubstitutionCipher.generateRandomSubstitutions();

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
        assertEquals("", SubstitutionCipher.encipher((String) null, SUBSTITUTIONS));
        assertArrayEquals(new byte[0], SubstitutionCipher.encipher((byte[]) null, SUBSTITUTIONS));

        // Empty
        assertEquals("", SubstitutionCipher.encipher("", SUBSTITUTIONS));
        assertArrayEquals(new byte[0], SubstitutionCipher.encipher(new byte[0]));

        // Whitespace
        String unencryptedString = "    ";
        assertEquals(unencryptedString, SubstitutionCipher.encipher(unencryptedString, SUBSTITUTIONS));

        // Valid
        unencryptedString = "This is an un-encrypted String.";
        assertEquals("\\ho| o| HV uVn0V@r6?.0d S.roV8t", SubstitutionCipher.encipher(unencryptedString, SUBSTITUTIONS));

        unencryptedString = "This is an0th3R un_encrypted, Str1N&...";
        assertEquals("\\ho| o| HVe.h$= uV_0V@r6?.0d, S.rANIttt", SubstitutionCipher.encipher(unencryptedString, SUBSTITUTIONS));

        unencryptedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 `~!@#$%^&*()-_=+[]{};:'\",<.>/?\\|";
        String expected = "Hz@d0f8ho*LlmVi?9r|.uvwx6b1]C[E;Ga&J!k<N'PQ=S\\U-W+YZeA`$4:y^#q 2~Kcg3%7Ij()n_RXDB{}F5O\",Mt>/pTs";
        assertEquals(expected, SubstitutionCipher.encipher(unencryptedString, SUBSTITUTIONS));

        unencryptedString = "This is a bad example (just kidding).";
        assertEquals("This is K mKd exKmple (just kidding).", SubstitutionCipher.encipher(unencryptedString, new char[] { 'K', 'm' }));
    }

    @Test
    public void testEncipher_randomSubstitutions() {
        // Null
        String encryptedString = SubstitutionCipher.encipher((String) null, RANDOM_SUBSTITUTIONS);
        assertEquals("", SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Empty
        String unencryptedString = "";
        encryptedString = SubstitutionCipher.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Whitespace
        unencryptedString = "    ";
        encryptedString = SubstitutionCipher.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Valid
        unencryptedString = "This is an un-encrypted String.";
        encryptedString = SubstitutionCipher.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        unencryptedString = "This is an0th3R un_encrypted, Str1N&...";
        encryptedString = SubstitutionCipher.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        unencryptedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 `~!@#$%^&*()-_=+[]{};:'\",<.>/?\\|";
        encryptedString = SubstitutionCipher.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));
    }

    @Test
    public void testDecipher() {
        // Null
        assertEquals("", SubstitutionCipher.decipher((String) null, SUBSTITUTIONS));
        assertArrayEquals(new byte[0], SubstitutionCipher.decipher((byte[]) null, SUBSTITUTIONS));

        // Empty
        assertEquals("", SubstitutionCipher.decipher("", SUBSTITUTIONS));
        assertArrayEquals(new byte[0], SubstitutionCipher.decipher(new byte[0], SUBSTITUTIONS));

        // Whitespace
        String encryptedString = "    ";
        assertEquals(encryptedString, SubstitutionCipher.decipher(encryptedString, SUBSTITUTIONS));

        // Valid
        encryptedString = "\\ho| o| HV uVn0V@r6?.0d S.roV8t";
        assertEquals("This is an un-encrypted String.", SubstitutionCipher.decipher(encryptedString, SUBSTITUTIONS));

        encryptedString = "\\ho| o| HVe.h$= uV_0V@r6?.0d, S.rANIttt";
        assertEquals("This is an0th3R un_encrypted, Str1N&...", SubstitutionCipher.decipher(encryptedString, SUBSTITUTIONS));

        encryptedString = "Hz@d0f8ho*LlmVi?9r|.uvwx6b1]C[E;Ga&J!k<N'PQ=S\\U-W+YZeA`$4:y^#q 2~Kcg3%7Ij()n_RXDB{}F5O\",Mt>/pTs";
        String expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 `~!@#$%^&*()-_=+[]{};:'\",<.>/?\\|";
        assertEquals(expected, SubstitutionCipher.decipher(encryptedString, SUBSTITUTIONS));

        encryptedString = "This is K mKd exKmple (just kidding).";
        assertEquals("This is a bad exabple (just kidding).", SubstitutionCipher.decipher(encryptedString, new char[] { 'K', 'm' }));
    }

    @Test
    public void testDecipher_randomSubstitutions() {
        // Null
        String encryptedString = SubstitutionCipher.encipher((String) null, RANDOM_SUBSTITUTIONS);
        assertEquals("", SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Empty
        String unencryptedString = "";
        encryptedString = SubstitutionCipher.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Whitespace
        unencryptedString = "    ";
        encryptedString = SubstitutionCipher.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        // Valid
        unencryptedString = "This is an un-encrypted String.";
        encryptedString = SubstitutionCipher.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        unencryptedString = "This is an0th3R un_encrypted, Str1N&...";
        encryptedString = SubstitutionCipher.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));

        unencryptedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 `~!@#$%^&*()-_=+[]{};:'\",<.>/?\\|";
        encryptedString = SubstitutionCipher.encipher(unencryptedString, RANDOM_SUBSTITUTIONS);
        assertEquals(unencryptedString, SubstitutionCipher.decipher(encryptedString, RANDOM_SUBSTITUTIONS));
    }

    @Test
    public void testGenerateRandomSubstitutions() {
        char[] subs = SubstitutionCipher.generateRandomSubstitutions();

        assertNotNull(subs);
        assertEquals(95, subs.length);
    }

}