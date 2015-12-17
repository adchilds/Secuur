package com.adamchilds.secuur.encryption.method.binary;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Implementation of basic encoding and decoding via the Base32 binary algorithm.
 *
 * @author Adam Childs
 * @since 1.0
 */
public class Base32 {

    public static final BaseEncoding ENCODER = BaseEncoding.base32();

    /**
     * Encodes the given array of bytes using the Base32 encoding algorithm.
     *
     * @param bytesToEncode the array of bytes to encode
     * @return a new array of encoded bytes
     */
    public static byte[] encode(byte[] bytesToEncode) {
        if (ArrayUtils.isEmpty(bytesToEncode)) {
            return new byte[0];
        }

        return ENCODER.encode(bytesToEncode).getBytes();
    }

    /**
     * Encodes the given {@link String} using the Base32 encoding algorithm.
     *
     * @param stringToEncode the String to encode
     * @return a new encoded {@link String}
     */
    public static String encode(String stringToEncode) {
        if (stringToEncode == null) {
            return "";
        }

        byte[] bytesToEncode = stringToEncode.getBytes();
        byte[] encodedBytes = encode(bytesToEncode);

        return new String(encodedBytes, Charsets.UTF_8);
    }

    /**
     * Decodes the given array of bytes using the Base32 decoding algorithm.
     *
     * @param bytesToDecode the array of bytes to encode
     * @return a new array of decoded bytes
     */
    public static byte[] decode(byte[] bytesToDecode) {
        if (ArrayUtils.isEmpty(bytesToDecode)) {
            return new byte[0];
        }

        String decodedString = decode(new String(bytesToDecode, Charsets.UTF_8));
        return decodedString.getBytes();
    }

    /**
     * Decodes the given {@link String} using the Base32 decoding algorithm.
     *
     * @param stringToDecode the {@link String} to decode
     * @return a new decoded {@link String}
     */
    public static String decode(String stringToDecode) {
        if (stringToDecode == null) {
            return "";
        }

        byte[] decodedBytes = ENCODER.decode(stringToDecode);
        return new String(decodedBytes, Charsets.UTF_8);
    }

    private Base32() {}

}