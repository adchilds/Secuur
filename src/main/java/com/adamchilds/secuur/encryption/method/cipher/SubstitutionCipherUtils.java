package com.adamchilds.secuur.encryption.method.cipher;

import com.adamchilds.secuur.encryption.EncryptionOptions;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Note: If supplying your own substitution chars, make sure to always supply all 95 characters.
 *
 * chars = [ a : K, b : m ]
 * start = This is a bad example (just kidding).
 * enciphered = This is K mKd exKmple (just kidding).
 * deciphered = This is a bad exabple (just kidding).
 */
public class SubstitutionCipherUtils {
    private static final Logger logger = LoggerFactory.getLogger(SubstitutionCipherUtils.class);

    public static final List<Character> VALID_CHARACTERS = Lists.newArrayList(
            // ASCII
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',

            // Special Characters
            '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+',
            '[', ']', '{', '}', ';', ':', ',', '.', '<', '>', '/', '?', '\'', '"', '\\', '|', ' ',

            // Numbers
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    );

    /**
     *
     * @param bytesToEncrypt
     * @return
     */
    public static byte[] encipher(byte[] bytesToEncrypt) {
        return encipher(bytesToEncrypt, EncryptionOptions.getDefault());
    }

    /**
     *
     * @param bytesToEncrypt
     * @param encryptionOptions
     * @return
     */
    public static byte[] encipher(byte[] bytesToEncrypt, EncryptionOptions encryptionOptions) {
        return encipher(bytesToEncrypt, encryptionOptions.getSubstitutionChars());
    }

    /**
     *
     * @param stringToEncrypt
     * @param substitutionChars
     * @return
     */
    public static String encipher(String stringToEncrypt, char[] substitutionChars) {
        if (stringToEncrypt == null) {
            return "";
        }

        byte[] bytesToEncrypt = stringToEncrypt.getBytes();
        byte[] encryptedBytes = encipher(bytesToEncrypt, substitutionChars);

        return new String(encryptedBytes, Charsets.UTF_8);
    }

    /**
     *
     * @param bytesToEncrypt
     * @param substitutionChars
     * @return
     */
    public static byte[] encipher(byte[] bytesToEncrypt, char[] substitutionChars) {
        if (ArrayUtils.isEmpty(bytesToEncrypt)) {
            return new byte[0];
        }

        String bytesAsString = new String(bytesToEncrypt, Charsets.UTF_8);

        StringBuilder encryptedStringBuilder = new StringBuilder();
        for (char c : bytesAsString.toCharArray()) {
            // Find the index of the character in the list of VALID_CHARACTERS
            int charIndex = VALID_CHARACTERS.indexOf(c);

            // Use that index to find its substitution
            try {
                encryptedStringBuilder.append(substitutionChars[charIndex]);
            } catch (IndexOutOfBoundsException iooe) {
                logger.debug("Could not find substitution for character=[{}]", c);

                // Use the same character since a substitution wasn't found
                encryptedStringBuilder.append(c);
            }
        }

        // Make sure the encrypted string has characters
        String encryptedString = encryptedStringBuilder.toString();
        return encryptedString.getBytes();
    }

    /**
     *
     * @param bytesToDecrypt
     * @param substitutionChars
     * @return
     */
    public static byte[] decipher(byte[] bytesToDecrypt, char[] substitutionChars) {
        if (ArrayUtils.isEmpty(bytesToDecrypt)) {
            return new byte[0];
        }

        String bytesAsString = new String(bytesToDecrypt, Charsets.UTF_8);
        List<Character> substitutionCharList = Chars.asList(substitutionChars);

        StringBuilder decryptedStringBuilder = new StringBuilder();
        for (char c : bytesAsString.toCharArray()) {
            // Find the index of the character in the list of VALID_CHARACTERS
            int charIndex = substitutionCharList.indexOf(c);

            // Use that index to find its substitution
            try {
                decryptedStringBuilder.append(VALID_CHARACTERS.get(charIndex));
            } catch (IndexOutOfBoundsException iooe) {
                logger.debug("Could not find substitution for character=[{}]", c);

                // Use the same character since a substitution wasn't found
                decryptedStringBuilder.append(c);
            }
        }

        String encryptedString = decryptedStringBuilder.toString();
        return encryptedString.getBytes();
    }

    /**
     *
     * @param stringToDecrypt
     * @param substitutionChars
     * @return
     */
    public static String decipher(String stringToDecrypt, char[] substitutionChars) {
        if (stringToDecrypt == null) {
            return "";
        }

        byte[] bytesToDecrypt = stringToDecrypt.getBytes();
        byte[] decryptedBytes = decipher(bytesToDecrypt, substitutionChars);

        return new String(decryptedBytes, Charsets.UTF_8);
    }

    /**
     *
     * @return
     */
    public static char[] generateRandomSubstitutions() {
        List<Character> validCharsCopy = Lists.newArrayList(VALID_CHARACTERS);

        // Randomly shuffle the array
        Collections.shuffle(validCharsCopy);

        char[] substitutionChars = new char[VALID_CHARACTERS.size()];
        for (int i = 0; i < validCharsCopy.size(); i++) {
            substitutionChars[i] = validCharsCopy.get(i);
        }

        return substitutionChars;
    }

    private SubstitutionCipherUtils() {}

}