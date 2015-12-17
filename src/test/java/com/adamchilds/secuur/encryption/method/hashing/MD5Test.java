package com.adamchilds.secuur.encryption.method.hashing;

import com.adamchilds.secuur.util.SecuurByteUtils;
import com.google.common.base.Charsets;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link MD5} class.
 */
public class MD5Test {

    @Test
    public void testEncrypt_nullBytes() {
        byte[] encryptedBytes = MD5.encrypt((byte[]) null);
        assertNotNull(encryptedBytes);
        assertEquals(0, encryptedBytes.length);
    }

    @Test
    public void testEncrypt_nullString() {
        String encryptedString = MD5.encrypt((String) null);
        assertNotNull(encryptedString);
        assertEquals(0, encryptedString.length());
    }

    @Test
    public void testEncrypt_emptyBytes() {
        byte[] encryptedBytes = MD5.encrypt(new byte[0]);
        assertNotNull(encryptedBytes);
        assertEquals("D41D8CD98F00B204E9800998ECF8427E", SecuurByteUtils.toHexString(encryptedBytes));
    }

    @Test
    public void testEncrypt_emptyString() {
        assertEquals("D41D8CD98F00B204E9800998ECF8427E", MD5.encrypt(""));
        assertEquals("0CF31B2C283CE3431794586DF7B0996D", MD5.encrypt("    "));
    }

    @Test
    public void testEncrypt_validBytes() {
        byte[] bytesToEncrypt = "This is a test string.".getBytes(Charsets.UTF_8);
        byte[] encryptedBytes = MD5.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("1620D7B066531F9DBAD51EEE623F7635", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("D41D8CD98F00B204E9800998ECF8427E", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "    ".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("0CF31B2C283CE3431794586DF7B0996D", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()`~-_=+[{]}\\|;:'\",<.>/?".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("0ACC489264472811C60942C983E133D7", SecuurByteUtils.toHexString(encryptedBytes));
    }

}