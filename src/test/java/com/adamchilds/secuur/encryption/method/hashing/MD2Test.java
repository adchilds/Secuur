package com.adamchilds.secuur.encryption.method.hashing;

import com.adamchilds.secuur.util.SecuurByteUtils;
import com.google.common.base.Charsets;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link MD2} class.
 */
public class MD2Test {

    @Test
    public void testEncrypt_nullBytes() {
        byte[] encryptedBytes = MD2.encrypt((byte[]) null);
        assertNotNull(encryptedBytes);
        assertEquals(0, encryptedBytes.length);
    }

    @Test
    public void testEncrypt_nullString() {
        String encryptedString = MD2.encrypt((String) null);
        assertNotNull(encryptedString);
        assertEquals(0, encryptedString.length());
    }

    @Test
    public void testEncrypt_emptyBytes() {
        byte[] encryptedBytes = MD2.encrypt(new byte[0]);
        assertNotNull(encryptedBytes);
        assertEquals("8350E5A3E24C153DF2275C9F80692773", SecuurByteUtils.toHexString(encryptedBytes));
    }

    @Test
    public void testEncrypt_emptyString() {
        assertEquals("8350E5A3E24C153DF2275C9F80692773", MD2.encrypt(""));
        assertEquals("272285925A709BC825A4534619942366", MD2.encrypt("    "));
    }

    @Test
    public void testEncrypt_validBytes() {
        byte[] bytesToEncrypt = "This is a test string.".getBytes(Charsets.UTF_8);
        byte[] encryptedBytes = MD2.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("7644A86604E9AE9DE16965282DB9A77A", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "".getBytes(Charsets.UTF_8);
        encryptedBytes = MD2.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("8350E5A3E24C153DF2275C9F80692773", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "    ".getBytes(Charsets.UTF_8);
        encryptedBytes = MD2.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("272285925A709BC825A4534619942366", SecuurByteUtils.toHexString(encryptedBytes));

        bytesToEncrypt = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()`~-_=+[{]}\\|;:'\",<.>/?".getBytes(Charsets.UTF_8);
        encryptedBytes = MD2.encrypt(bytesToEncrypt);
        assertNotNull(encryptedBytes);
        assertEquals("8C54716F0F076EC7A3C79EB3B3EB2DBC", SecuurByteUtils.toHexString(encryptedBytes));
    }

}