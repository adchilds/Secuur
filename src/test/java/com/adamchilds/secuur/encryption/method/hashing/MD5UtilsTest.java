package com.adamchilds.secuur.encryption.method.hashing;

import com.adamchilds.secuur.util.SecuurByteUtils;
import com.google.common.base.Charsets;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link MD5Utils} class.
 */
public class MD5UtilsTest {

    @Test
    public void testEncrypt() {
        // Null
        byte[] encryptedBytes = MD5Utils.encrypt(null);
        assertNotNull(encryptedBytes);
        assertEquals(0, encryptedBytes.length);

        // Empty
        encryptedBytes = MD5Utils.encrypt(new byte[0]);
        assertNotNull(encryptedBytes);
        assertEquals("D41D8CD98F00B204E9800998ECF8427E", SecuurByteUtils.toHexString(encryptedBytes));

        // Valid
        byte[] bytesToEncrypt = "This is a test string.".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5Utils.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("1620D7B066531F9DBAD51EEE623F7635", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5Utils.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("D41D8CD98F00B204E9800998ECF8427E", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "    ".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5Utils.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("0CF31B2C283CE3431794586DF7B0996D", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()`~-_=+[{]}\\|;:'\",<.>/?".getBytes(Charsets.UTF_8);
        encryptedBytes = MD5Utils.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("0ACC489264472811C60942C983E133D7", SecuurByteUtils.toHexString(encryptedBytes));
    }

}