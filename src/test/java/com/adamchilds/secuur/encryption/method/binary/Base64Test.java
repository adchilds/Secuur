package com.adamchilds.secuur.encryption.method.binary;

import com.google.common.base.Charsets;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link Base64} class.
 */
public class Base64Test {

    @Test
    public void testEncode_nullString() {
        assertEquals("", Base64.encode((String) null));
    }

    @Test
    public void testEncode_nullBytes() {
        assertArrayEquals(new byte[0], Base64.encode((byte[]) null));
    }

    @Test
    public void testEncode_emptyString() {
        assertEquals("", Base64.encode(""));
        assertEquals("ICAgIA==", Base64.encode("    "));
    }

    @Test
    public void testEncode_emptyBytes() {
        assertArrayEquals(new byte[0], Base64.encode(new byte[0]));
        assertEquals("", new String(Base64.encode(new byte[0]), Charsets.UTF_8));
    }

    @Test
    public void testEncode_validString() {
        assertEquals("VGhpcyBpcyBhIHRlc3Qu", Base64.encode("This is a test."));
        assertEquals("N2gxJCBpcyA0bjB0aDNyIHQzJHQuLi4=", Base64.encode("7h1$ is 4n0th3r t3$t..."));
    }

    @Test
    public void testEncode_validBytes() {
        byte[] bytesOfString = "This is a test.".getBytes();
        assertArrayEquals("VGhpcyBpcyBhIHRlc3Qu".getBytes(), Base64.encode(bytesOfString));

        bytesOfString = "7h1$ is 4n0th3r t3$t...".getBytes();
        assertArrayEquals("N2gxJCBpcyA0bjB0aDNyIHQzJHQuLi4=".getBytes(), Base64.encode(bytesOfString));
    }

    @Test
    public void testDecode_nullString() {
        assertEquals("", Base64.decode((String) null));
    }

    @Test
    public void testDecode_nullBytes() {
        assertArrayEquals(new byte[0], Base64.decode((byte[]) null));
    }

    @Test
    public void testDecode_emptyString() {
        assertEquals("", Base64.decode(""));
        assertEquals("    ", Base64.decode("ICAgIA=="));
    }

    @Test
    public void testDecode_emptyBytes() {
        assertArrayEquals(new byte[0], Base64.decode(new byte[0]));
        assertEquals("", new String(Base64.decode(new byte[0]), Charsets.UTF_8));
    }

    @Test
    public void testDecode_validString() {
        assertEquals("This is a test.", Base64.decode("VGhpcyBpcyBhIHRlc3Qu"));
        assertEquals("7h1$ is 4n0th3r t3$t...", Base64.decode("N2gxJCBpcyA0bjB0aDNyIHQzJHQuLi4="));
    }

    @Test
    public void testDecode_validBytes() {
        byte[] bytesOfString = "VGhpcyBpcyBhIHRlc3Qu".getBytes();
        assertArrayEquals("This is a test.".getBytes(), Base64.decode(bytesOfString));

        bytesOfString = "N2gxJCBpcyA0bjB0aDNyIHQzJHQuLi4=".getBytes();
        assertArrayEquals("7h1$ is 4n0th3r t3$t...".getBytes(), Base64.decode(bytesOfString));
    }

}