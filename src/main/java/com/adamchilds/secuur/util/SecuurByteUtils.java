package com.adamchilds.secuur.util;

/**
 * Provides useful utility functions for dealing with bytes and byte arrays.
 *
 * @author Adam Childs
 */
public class SecuurByteUtils {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    /**
     * Converts the given array of {@code md5HashedBytes} to a hex {@link String}.
     *
     * @param md5HashedBytes an MD5 hashed array of bytes
     * @return a new hex {@link String} representing the hashed bytes
     */
    public static String toHexString(byte[] md5HashedBytes) {
        if (md5HashedBytes == null) {
            return "";
        }

        char[] hexChars = new char[md5HashedBytes.length * 2];

        for (int i = 0; i < md5HashedBytes.length; i++) {
            int offset = md5HashedBytes[i] & 0xFF;

            hexChars[i * 2] = HEX_ARRAY[offset >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[offset & 0x0F];
        }

        return new String(hexChars);
    }

    private SecuurByteUtils() {}

}