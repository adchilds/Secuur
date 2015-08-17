package com.adamchilds.secuur.encryption.method.hashing;

import com.adamchilds.secuur.encryption.EncryptionOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public class MD2Utils {
    private static final Logger logger = LoggerFactory.getLogger(MD2Utils.class);

    /**
     * Encrypts the given array of bytes using the MD5 hashing algorithm.
     *
     * @param bytesToEncrypt the bytes to encrypt
     * @return an MD5 hashed array of bytes
     */
    public static byte[] encrypt(byte[] bytesToEncrypt) {
        return encrypt(bytesToEncrypt, EncryptionOptions.getDefault());
    }

    /**
     * Encrypts the given array of bytes using the MD2 hashing algorithm.
     *
     * @param bytesToEncrypt the bytes to encrypt
     * @param encryptionOptions
     * @return an MD5 hashed array of bytes
     */
    public static byte[] encrypt(byte[] bytesToEncrypt, EncryptionOptions encryptionOptions) {
        if (bytesToEncrypt == null) {
            return new byte[0];
        }

        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD2");
        } catch (NoSuchAlgorithmException nsae) {
            logger.error("Could not find MD2 digest.", nsae);

            return new byte[0];
        }

        return messageDigest.digest(bytesToEncrypt);
    }

    private MD2Utils() {}

}