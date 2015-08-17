package com.adamchilds.secuur.encryption.method.hashing;

import com.adamchilds.secuur.util.SecuurByteUtils;
import com.google.common.base.Charsets;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link MD5Utils} class.
 */
public class MD2UtilsTest {

    @Test
    public void testEncrypt() {
        // Null
        byte[] encryptedBytes = MD2Utils.encrypt(null);
        assertNotNull(encryptedBytes);
        assertEquals(0, encryptedBytes.length);

        // Empty
        encryptedBytes = MD2Utils.encrypt(new byte[0]);
        assertNotNull(encryptedBytes);
        assertEquals("8350E5A3E24C153DF2275C9F80692773", SecuurByteUtils.toHexString(encryptedBytes));

        // Valid
        byte[] bytesToEncrypt = "This is a test string.".getBytes(Charsets.UTF_8);
        encryptedBytes = MD2Utils.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("7644A86604E9AE9DE16965282DB9A77A", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "".getBytes(Charsets.UTF_8);
        encryptedBytes = MD2Utils.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("8350E5A3E24C153DF2275C9F80692773", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "    ".getBytes(Charsets.UTF_8);
        encryptedBytes = MD2Utils.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("272285925A709BC825A4534619942366", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()`~-_=+[{]}\\|;:'\",<.>/?".getBytes(Charsets.UTF_8);
        encryptedBytes = MD2Utils.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("8C54716F0F076EC7A3C79EB3B3EB2DBC", SecuurByteUtils.toHexString(encryptedBytes));
    }

}