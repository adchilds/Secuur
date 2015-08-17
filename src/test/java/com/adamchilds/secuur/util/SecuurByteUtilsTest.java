package com.adamchilds.secuur.util;

import com.adamchilds.secuur.encryption.method.hashing.MD5Utils;
import com.google.common.base.Charsets;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link SecuurByteUtils} class.
 */
public class SecuurByteUtilsTest {

    @Test
    public void testToHexString() {
        // Null
        assertEquals("", SecuurByteUtils.toHexString(null));

        byte[] encryptedBytes = MD5Utils.encrypt(null);
        String hexString = SecuurByteUtils.toHexString(encryptedBytes);
        assertNotNull(hexString);
        assertEquals("", hexString);

        // Empty
        encryptedBytes = MD5Utils.encrypt(new byte[0]);
        hexString = SecuurByteUtils.toHexString(encryptedBytes);
        assertNotNull(hexString);
        assertEquals("D41D8CD98F00B204E9800998ECF8427E", hexString);

        // Valid
        byte[] bytesToEncrypt = "This is a test string.".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5Utils.encrypt(bytesToEncrypt);
        hexString = SecuurByteUtils.toHexString(encryptedBytes);
        assertNotNull(hexString);
        assertEquals("1620D7B066531F9DBAD51EEE623F7635", hexString);

        bytesToEncrypt = "".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5Utils.encrypt(bytesToEncrypt);
        hexString = SecuurByteUtils.toHexString(encryptedBytes);
        assertNotNull(hexString);
        assertEquals("D41D8CD98F00B204E9800998ECF8427E", hexString);

        bytesToEncrypt = "    ".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5Utils.encrypt(bytesToEncrypt);
        hexString = SecuurByteUtils.toHexString(encryptedBytes);
        assertNotNull(hexString);
        assertEquals("0CF31B2C283CE3431794586DF7B0996D", hexString);

        bytesToEncrypt = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()`~-_=+[{]}\\|;:'\",<.>/?".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5Utils.encrypt(bytesToEncrypt);
        hexString = SecuurByteUtils.toHexString(encryptedBytes);
        assertNotNull(hexString);
        assertEquals("0ACC489264472811C60942C983E133D7", hexString);
    }

}