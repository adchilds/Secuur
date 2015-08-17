package com.adamchilds.secuur.encryption.method.binary;

import com.google.common.base.Charsets;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link Base64Utils} class.
 */
public class Base32UtilsTest {

    @Test
    public void testEncode() {
        // Null
        assertEquals("", Base32Utils.encode((String) null));
        assertArrayEquals(new byte[0], Base32Utils.encode((byte[]) null));

        // Empty
        assertEquals("", Base32Utils.encode(""));
        assertArrayEquals(new byte[0], Base32Utils.encode(new byte[0]));
        assertEquals("", new String(Base32Utils.encode(new byte[0]), Charsets.UTF_8));
        assertEquals("EAQCAIA=", Base32Utils.encode("    "));

        // Valid
        assertEquals("KRUGS4ZANFZSAYJAORSXG5BO", Base32Utils.encode("This is a test."));
        assertEquals("G5UDCJBANFZSANDOGB2GQM3SEB2DGJDUFYXC4===", Base32Utils.encode("7h1$ is 4n0th3r t3$t..."));

        byte[] bytesOfString = "This is a test.".getBytes();
        assertArrayEquals("KRUGS4ZANFZSAYJAORSXG5BO".getBytes(), Base32Utils.encode(bytesOfString));
    }

    @Test
    public void testDecode() {
        // Null
        assertEquals("", Base32Utils.decode((String) null));
        assertArrayEquals(new byte[0], Base32Utils.decode((byte[]) null));

        // Empty

        // Valid

    }

}