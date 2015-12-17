package com.adamchilds.secuur.encryption.method.cipher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link BaconianCipher} class.
 */
public class BaconianCipherTest {

    @Test
    public void testEncipher() {
        // Null
        assertEquals("", BaconianCipher.encipher((String) null));
        assertArrayEquals(new byte[0], BaconianCipher.encipher((byte[]) null));

        // Empty
        assertEquals("", BaconianCipher.encipher(""));
        assertArrayEquals(new byte[0], BaconianCipher.encipher(new byte[0]));
        assertEquals("", BaconianCipher.encipher("    "));

        // Valid
        assertEquals("baabaaabbbabaaabaaababaaabaaabaaaaabaabaaabaabaaabbaaba", BaconianCipher.encipher("This is a test."));
        assertEquals("baabaaabbbabaaaaaaaaabbaabaabaaabbbbaaaabaababaaabbaaba", BaconianCipher.encipher("Th1$ i5 an0th3r t3st."));
    }

    @Test
    public void testDecipher() {
        // Null
        assertEquals("", BaconianCipher.decipher((String) null));
        assertArrayEquals(new byte[0], BaconianCipher.decipher((byte[]) null));

        // Empty
        assertEquals("", BaconianCipher.decipher(""));
        assertArrayEquals(new byte[0], BaconianCipher.decipher(new byte[0]));
        assertEquals("", BaconianCipher.decipher("    "));

        // Valid
        assertEquals("thisisatest", BaconianCipher.decipher("baabaaabbbabaaabaaababaaabaaabaaaaabaabaaabaabaaabbaaba"));
        assertEquals("thianthrtst", BaconianCipher.decipher("baabaaabbbabaaaaaaaaabbaabaabaaabbbbaaaabaababaaabbaaba"));
    }

}