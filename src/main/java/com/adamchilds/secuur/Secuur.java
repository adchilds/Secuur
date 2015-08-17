package com.adamchilds.secuur;

import com.adamchilds.secuur.encryption.EncryptionOptions;
import com.adamchilds.secuur.encryption.method.binary.Base32Utils;
import com.adamchilds.secuur.encryption.method.binary.Base64Utils;
import com.adamchilds.secuur.encryption.method.cipher.AutokeyCipherUtils;
import com.adamchilds.secuur.encryption.method.cipher.BaconianCipherUtils;
import com.adamchilds.secuur.encryption.method.cipher.CaesarCipherUtils;
import com.adamchilds.secuur.encryption.method.cipher.SubstitutionCipherUtils;
import com.adamchilds.secuur.encryption.method.hashing.MD2Utils;
import com.adamchilds.secuur.encryption.method.hashing.MD5Utils;
import com.adamchilds.secuur.exception.CannotEncryptException;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 */
public class Secuur {
    private static final Logger logger = LoggerFactory.getLogger(Secuur.class);

    /**
     *
     * @param bytesToEncrypt
     * @param encryptionOptions the encryption options, including the method to use (RSA, MD5, etc.)
     * @return
     */
    private static byte[] encrypt(byte[] bytesToEncrypt, EncryptionOptions encryptionOptions) {
        if (bytesToEncrypt == null) {
            return new byte[0];
        }

        switch(encryptionOptions.getEncryptionType()) {
            case ASYMMETRIC_RSA:
                break;

            case BINARY_BASE32:
                return Base32Utils.encode(bytesToEncrypt);

            case BINARY_BASE64:
                return Base64Utils.encode(bytesToEncrypt);

            case CIPHER_AUTOKEY:
                return AutokeyCipherUtils.encipher(bytesToEncrypt);

            case CIPHER_BACONIAN:
                return BaconianCipherUtils.encipher(bytesToEncrypt);

            case CIPHER_CAESAR:
                return CaesarCipherUtils.encipher(bytesToEncrypt);

            case CIPHER_SUBSTITUTION:
                return SubstitutionCipherUtils.encipher(bytesToEncrypt);

            case HASH_MD2:
                return MD2Utils.encrypt(bytesToEncrypt);

            case HASH_MD5:
                return MD5Utils.encrypt(bytesToEncrypt);

            case HASH_SHA:
                break;

            case SYMMETRIC_AES:
                break;

            case SYMMETRIC_DES:
                break;

            case SYMMETRIC_IDEA:
                break;

            default:
                break;
        }

        return bytesToEncrypt;
    }

    /**
     *
     * @param objectToEncrypt
     * @param encryptionOptions the encryption options, including the method to use (RSA, MD5, etc.)
     * @return
     */
    public static Object encrypt(Object objectToEncrypt, EncryptionOptions encryptionOptions) {
        if (objectToEncrypt == null) {
            return null;
        }

        // Determine which encryption function to call
        if (objectToEncrypt instanceof File) {
            return encryptFile((File) objectToEncrypt, encryptionOptions);
        } else if (objectToEncrypt instanceof String) {
            return encryptString((String) objectToEncrypt, encryptionOptions);
        }

        return encryptObject(objectToEncrypt, encryptionOptions);
    }

    /**
     * Encrypts the bytes of the given {@link File} using the given {@link EncryptionOptions#encryptionType}.
     *
     * @param file the file to encrypt
     * @param encryptionOptions the encryption options, including the method to use (RSA, MD5, etc.)
     * @return a new {@link File} where the bytes are encrypted
     */
    private static File encryptFile(File file, EncryptionOptions encryptionOptions) {
        if (file == null) {
            logger.warn("Could not encrypt file, it is null.");

            return null;
        }

        // Read the bytes from the file
        byte[] fileBytes = null;
        try {
            fileBytes = Files.toByteArray(file);
        } catch (IOException ioe) {
            logger.error("Could not convert File object to byte array. file=[" + file.getAbsolutePath() + "]", ioe);
        }

        // Encrypt the bytes
        byte[] encryptedBytes = encrypt(fileBytes, encryptionOptions);

        // Create a temp file
        File tempEncryptedFile;
        try {
            tempEncryptedFile = File.createTempFile("encrypted_file", "tmp");
        } catch (IOException ioe) {
            logger.error("Could not generate temp file.", ioe);

            return null;
        }

        // Write the encrypted bytes to the temp file
        try {
            Files.write(encryptedBytes, tempEncryptedFile);
        } catch (IOException ioe) {
            logger.error("Could not write encrypted bytes to temp file.", ioe);
        }

        return tempEncryptedFile;
    }

    /**
     *
     * @param objectToEncrypt
     * @param encryptionOptions
     * @return
     */
    private static byte[] encryptObject(Object objectToEncrypt, EncryptionOptions encryptionOptions) {
        if (!(objectToEncrypt instanceof Serializable)) {
            throw new CannotEncryptException("Cannot encrypt the given Object; it's not Serializable.");
        }

        // Convert the Object to a byte array
        byte[] bytesToEncrypt;
        try {
            bytesToEncrypt = SerializationUtils.serialize((Serializable) objectToEncrypt);
        } catch (SerializationException se) {
            throw new CannotEncryptException("Cannot encrypt the given Object; it's not Serializable.", se);
        } catch (Exception e) {
            throw new CannotEncryptException("Cannot encrypt the given Object.", e);
        }

        // Make sure the Object was converted to bytes successfully
        if (bytesToEncrypt == null || bytesToEncrypt.length == 0) {
            throw new CannotEncryptException("Could not encrypt the given Object; serialization failed.");
        }

        // Encrypt the bytes
        return encrypt(bytesToEncrypt, encryptionOptions);
    }

    /**
     *
     * @param stringToEncrypt
     * @param encryptionOptions
     * @return
     */
    private static String encryptString(String stringToEncrypt, EncryptionOptions encryptionOptions) {
        if (StringUtils.isBlank(stringToEncrypt)) {
            return "";
        }

        byte[] encryptedBytes = encrypt(stringToEncrypt.getBytes(), encryptionOptions);

        return new String(encryptedBytes, Charsets.UTF_8);
    }

    /**
     *
     * @param objectToDecrypt
     * @param encryptionOptions the encryption options, including the method to use (RSA, MD5, etc.)
     * @return
     */
    public static Object decrypt(Object objectToDecrypt, EncryptionOptions encryptionOptions) {
        return new Object();
    }

    private Secuur() {}

}