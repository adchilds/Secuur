package com.adamchilds.secuur.encryption.method.cipher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link BaconianCipherUtils} class.
 */
public class BaconianCipherUtilsTest {

    @Test
    public void testEncipher() {
        // Null
        assertEquals("", BaconianCipherUtils.encipher((String) null));
        assertArrayEquals(new byte[0], BaconianCipherUtils.encipher((byte[]) null));

        // Empty
        assertEquals("", BaconianCipherUtils.encipher(""));
        assertArrayEquals(new byte[0], BaconianCipherUtils.encipher(new byte[0]));
        assertEquals("", BaconianCipherUtils.encipher("    "));

        // Valid
        assertEquals("baabaaabbbabaaabaaababaaabaaabaaaaabaabaaabaabaaabbaaba", BaconianCipherUtils.encipher("This is a test."));
        assertEquals("baabaaabbbabaaaaaaaaabbaabaabaaabbbbaaaabaababaaabbaaba", BaconianCipherUtils.encipher("Th1$ i5 an0th3r t3st."));
    }

    @Test
    public void testDecipher() {
        // Null
        assertEquals("", BaconianCipherUtils.decipher((String) null));
        assertArrayEquals(new byte[0], BaconianCipherUtils.decipher((byte[]) null));

        // Empty
        assertEquals("", BaconianCipherUtils.decipher(""));
        assertArrayEquals(new byte[0], BaconianCipherUtils.decipher(new byte[0]));
        assertEquals("", BaconianCipherUtils.decipher("    "));

        // Valid
        assertEquals("thisisatest", BaconianCipherUtils.decipher("baabaaabbbabaaabaaababaaabaaabaaaaabaabaaabaabaaabbaaba"));
        assertEquals("thianthrtst", BaconianCipherUtils.decipher("baabaaabbbabaaaaaaaaabbaabaabaaabbbbaaaabaababaaabbaaba"));
    }

}