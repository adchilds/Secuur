package com.adamchilds.secuur.encryption.method.cipher;

import com.adamchilds.secuur.encryption.EncryptionOptions;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.Random;

/**
 *
 */
public class CaesarCipher {

    public static final List<Character> VALID_CHARACTERS = Lists.newArrayList(
            // Lowercase
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',

            // Uppercase
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    );

    private static final int VALID_CHARS_LENGTH = 26;

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
        return encipher(bytesToEncrypt, encryptionOptions.getShift());
    }

    /**
     *
     * @param bytesToEncrypt
     * @param shift
     * @return
     */
    public static byte[] encipher(byte[] bytesToEncrypt, int shift) {
        if (ArrayUtils.isEmpty(bytesToEncrypt)) {
            return new byte[0];
        }

        // Normalize the shift
        shift = shift % VALID_CHARS_LENGTH + VALID_CHARS_LENGTH;

        String stringToEncrypt = new String(bytesToEncrypt, Charsets.UTF_8);

        StringBuilder encryptedStringBuilder = new StringBuilder();
        for (char c : stringToEncrypt.toCharArray()) {
            if (VALID_CHARACTERS.contains(c)) {
                char shiftedChar;
                if (Character.isLowerCase(c)) {
                    shiftedChar = (char) ('a' + (c - 'a' + shift) % 26);
                } else {
                    shiftedChar = (char) ('A' + (c - 'A' + shift) % 26);
                }

                encryptedStringBuilder.append(shiftedChar);
            } else {
                encryptedStringBuilder.append(c);
            }
        }

        String encryptedString = encryptedStringBuilder.toString();
        return encryptedString.getBytes();
    }

    /**
     *
     * @param stringToEncrypt
     * @param shift
     * @return
     */
    public static String encipher(String stringToEncrypt, int shift) {
        if (stringToEncrypt == null) {
            return "";
        }

        byte[] bytesToEncrypt = stringToEncrypt.getBytes();
        byte[] encryptedBytes = encipher(bytesToEncrypt, shift);

        return new String(encryptedBytes, Charsets.UTF_8);
    }

    /**
     *
     * @param bytesToDecrypt
     * @return
     */
    public static byte[] decipher(byte[] bytesToDecrypt) {
        return decipher(bytesToDecrypt, EncryptionOptions.getDefault());
    }

    /**
     *
     * @param bytesToDecrypt
     * @param encryptionOptions
     * @return
     */
    public static byte[] decipher(byte[] bytesToDecrypt, EncryptionOptions encryptionOptions) {
        return decipher(bytesToDecrypt, encryptionOptions.getShift());
    }

    /**
     *
     * @param bytesToDecrypt
     * @param shift
     * @return
     */
    public static byte[] decipher(byte[] bytesToDecrypt, int shift) {
        return encipher(bytesToDecrypt, VALID_CHARS_LENGTH - shift);
    }

    /**
     *
     * @param stringToDecrypt
     * @param shift
     * @return
     */
    public static String decipher(String stringToDecrypt, int shift) {
        if (stringToDecrypt == null) {
            return "";
        }

        byte[] bytesToDecrypt = stringToDecrypt.getBytes();
        byte[] decryptedBytes = decipher(bytesToDecrypt, shift);

        return new String(decryptedBytes, Charsets.UTF_8);
    }

    /**
     *
     * @return
     */
    public static int generateRandomShift() {
        return new Random().nextInt(VALID_CHARS_LENGTH) + 1;
    }

    private CaesarCipher() {}

}