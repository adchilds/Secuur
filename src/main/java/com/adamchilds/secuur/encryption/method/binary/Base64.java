package com.adamchilds.secuur.encryption.method.binary;

import com.adamchilds.secuur.exception.CannotDecodeException;
import com.google.common.base.Charsets;
import org.apache.commons.lang3.ArrayUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Implementation of basic encoding and decoding via the Base64 binary algorithm.
 *
 * @author Adam Childs
 * @since 1.0
 */
public class Base64 {

    public static final BASE64Encoder ENCODER = new BASE64Encoder();
    public static final BASE64Decoder DECODER = new BASE64Decoder();

    /**
     * Encodes the given array of bytes using the Base64 encoding algorithm.
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
     * Encodes the given {@link String} using the Base64 encoding algorithm.
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
     * Decodes the given array of bytes using the Base64 decoding algorithm.
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
     * Decodes the given {@link String} using the Base64 decoding algorithm.
     *
     * @param stringToDecode the {@link String} to decode
     * @return a new decoded {@link String}
     */
    public static String decode(String stringToDecode) {
        if (stringToDecode == null) {
            return "";
        }

        byte[] decodedBytes;
        try {
            decodedBytes = DECODER.decodeBuffer(stringToDecode);
        } catch (IOException ioe) {
            throw new CannotDecodeException("Could not decode [" + stringToDecode + "].", ioe);
        }

        return new String(decodedBytes, Charsets.UTF_8);
    }

    private Base64() {}

}