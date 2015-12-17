package com.adamchilds.secuur.encryption.method.hashing;

import com.adamchilds.secuur.util.SecuurByteUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Simple implementation using Java's default MessageDigest algorithm for MD2.
 *
 * @author Adam Childs
 * @since 1.0
 */
public class MD2 {

    /**
     * Encrypts the given array of bytes using the MD2 hashing algorithm.
     *
     * @param bytesToEncrypt the bytes to encrypt
     * @return an MD5 hashed array of bytes
     */
    public static byte[] encrypt(byte[] bytesToEncrypt) {
        if (bytesToEncrypt == null) {
            return new byte[0];
        }

        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD2");
        } catch (NoSuchAlgorithmException nsae) {
            return new byte[0];
        }

        return messageDigest.digest(bytesToEncrypt);
    }

    /**
     * Encrypts the given String using the MD2 hashing algorithm.
     *
     * @param stringToEncrypt the {@link String} to encrypt
     * @return an MD2 hashed {@link String}
     */
    public static String encrypt(String stringToEncrypt) {
        if (stringToEncrypt == null) {
            return "";
        }

        byte[] encryptedBytes = encrypt(stringToEncrypt.getBytes());

        return SecuurByteUtils.toHexString(encryptedBytes);
    }

    private MD2() {}

}