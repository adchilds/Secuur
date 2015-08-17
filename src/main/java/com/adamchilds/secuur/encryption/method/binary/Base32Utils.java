package com.adamchilds.secuur.encryption.method.binary;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class Base32Utils {
    private static final Logger logger = LoggerFactory.getLogger(Base64Utils.class);

    public static final BaseEncoding ENCODER = BaseEncoding.base32();

    /**
     *
     * @param bytesToEncode
     * @return
     */
    public static byte[] encode(byte[] bytesToEncode) {
        if (ArrayUtils.isEmpty(bytesToEncode)) {
            return new byte[0];
        }


        String encodedString = ENCODER.encode(bytesToEncode);
        return encodedString.getBytes();
    }

    /**
     *
     * @param stringToEncode
     * @return
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
     *
     * @param bytesToDecode
     * @return
     */
    public static byte[] decode(byte[] bytesToDecode) {
        if (ArrayUtils.isEmpty(bytesToDecode)) {
            return new byte[0];
        }

        String decodedString = decode(new String(bytesToDecode, Charsets.UTF_8));
        return decodedString.getBytes();
    }

    /**
     *
     * @param stringToDecode
     * @return
     */
    public static String decode(String stringToDecode) {
        if (stringToDecode == null) {
            return "";
        }

        byte[] decodedBytes = ENCODER.decode(stringToDecode);
        return new String(decodedBytes, Charsets.UTF_8);
    }

    private Base32Utils() {}

}