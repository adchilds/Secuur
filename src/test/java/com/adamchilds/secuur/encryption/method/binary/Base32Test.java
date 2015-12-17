package com.adamchilds.secuur.encryption.method.binary;

import com.google.common.base.Charsets;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link Base32} class.
 */
public class Base32Test {

    @Test
    public void testEncode_nullString() {
        assertEquals("", Base32.encode((String) null));
    }

    @Test
    public void testEncode_nullBytes() {
        assertArrayEquals(new byte[0], Base32.encode((byte[]) null));
    }

    @Test
    public void testEncode_emptyString() {
        assertEquals("", Base32.encode(""));
        assertEquals("EAQCAIA=", Base32.encode("    "));
    }

    @Test
    public void testEncode_emptyBytes() {
        assertArrayEquals(new byte[0], Base32.encode(new byte[0]));
        assertEquals("", new String(Base32.encode(new byte[0]), Charsets.UTF_8));
    }

    @Test
    public void testEncode_validString() {
        assertEquals("KRUGS4ZANFZSAYJAORSXG5BO", Base32.encode("This is a test."));
        assertEquals("G5UDCJBANFZSANDOGB2GQM3SEB2DGJDUFYXC4===", Base32.encode("7h1$ is 4n0th3r t3$t..."));
    }

    @Test
    public void testEncode_validBytes() {
        byte[] bytesOfString = "This is a test.".getBytes();
        assertArrayEquals("KRUGS4ZANFZSAYJAORSXG5BO".getBytes(), Base32.encode(bytesOfString));

        bytesOfString = "7h1$ is 4n0th3r t3$t...".getBytes();
        assertArrayEquals("G5UDCJBANFZSANDOGB2GQM3SEB2DGJDUFYXC4===".getBytes(), Base32.encode(bytesOfString));
    }

    @Test
    public void testDecode_nullString() {
        assertEquals("", Base32.decode((String) null));
    }

    @Test
    public void testDecode_nullBytes() {
        assertArrayEquals(new byte[0], Base32.decode((byte[]) null));
    }

    @Test
    public void testDecode_emptyString() {
        assertEquals("", Base32.decode(""));
        assertEquals("    ", Base32.decode("EAQCAIA="));
    }

    @Test
    public void testDecode_emptyBytes() {
        assertArrayEquals(new byte[0], Base32.decode(new byte[0]));
        assertEquals("", new String(Base32.decode(new byte[0]), Charsets.UTF_8));
    }

    @Test
    public void testDecode_validString() {
        assertEquals("This is a test.", Base32.decode("KRUGS4ZANFZSAYJAORSXG5BO"));
        assertEquals("7h1$ is 4n0th3r t3$t...", Base32.decode("G5UDCJBANFZSANDOGB2GQM3SEB2DGJDUFYXC4==="));
    }

    @Test
    public void testDecode_validBytes() {
        byte[] bytesOfString = "KRUGS4ZANFZSAYJAORSXG5BO".getBytes();
        assertArrayEquals("This is a test.".getBytes(), Base32.decode(bytesOfString));

        bytesOfString = "G5UDCJBANFZSANDOGB2GQM3SEB2DGJDUFYXC4===".getBytes();
        assertArrayEquals("7h1$ is 4n0th3r t3$t...".getBytes(), Base32.decode(bytesOfString));
    }

}