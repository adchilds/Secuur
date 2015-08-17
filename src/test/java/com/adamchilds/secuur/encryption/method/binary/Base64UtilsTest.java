package com.adamchilds.secuur.encryption.method.binary;

import com.google.common.base.Charsets;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link Base64Utils} class.
 */
public class Base64UtilsTest {

    @Test
    public void testEncode() {
        // Null
        assertEquals("", Base64Utils.encode((String) null));
        assertArrayEquals(new byte[0], Base64Utils.encode((byte[]) null));

        // Empty
        assertEquals("", Base64Utils.encode(""));
        assertArrayEquals(new byte[0], Base64Utils.encode(new byte[0]));
        assertEquals("", new String(Base64Utils.encode(new byte[0]), Charsets.UTF_8));
        assertEquals("ICAgIA==", Base64Utils.encode("    "));

        // Valid
        assertEquals("VGhpcyBpcyBhIHRlc3Qu", Base64Utils.encode("This is a test."));
        assertEquals("N2gxJCBpcyA0bjB0aDNyIHQzJHQuLi4=", Base64Utils.encode("7h1$ is 4n0th3r t3$t..."));

        byte[] bytesOfString = "This is a test.".getBytes();
        assertArrayEquals("VGhpcyBpcyBhIHRlc3Qu".getBytes(), Base64Utils.encode(bytesOfString));
    }

    @Test
    public void testDecode() {
        // Null
        assertEquals("", Base64Utils.decode((String) null));
        assertArrayEquals(new byte[0], Base64Utils.decode((byte[]) null));

        // Empty

        // Valid

    }

}