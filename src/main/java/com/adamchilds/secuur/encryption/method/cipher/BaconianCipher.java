package com.adamchilds.secuur.encryption.method.cipher;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Modified version of the Baconian Cipher. Replaced j and v with unique values.
 */
public class BaconianCipher {

    public static final BiMap<Character, String> ENCODING_MAP =
            new ImmutableBiMap.Builder<Character, String>()
                .put('a', "aaaaa").put('b', "aaaab").put('c', "aaaba").put('d', "aaabb")
                .put('e', "aabaa").put('f', "aabab").put('g', "aabba").put('h', "aabbb")
                .put('i', "abaaa").put('j', "bbabb").put('k', "abaab").put('l', "ababa")
                .put('m', "ababb").put('n', "abbaa").put('o', "abbab").put('p', "abbba")
                .put('q', "abbbb").put('r', "baaaa").put('s', "baaab").put('t', "baaba")
                .put('u', "baabb").put('v', "bbbbb").put('w', "babaa").put('x', "babab")
                .put('y', "babba").put('z', "babbb")
                .build();

    /**
     *
     * @param bytesToEncode
     * @return
     */
    public static byte[] encipher(byte[] bytesToEncode) {
        if (ArrayUtils.isEmpty(bytesToEncode)) {
            return new byte[0];
        }

        String encodedString = encipher(new String(bytesToEncode, Charsets.UTF_8));
        return encodedString.getBytes();
    }

    /**
     *
     * @param stringToEncode
     * @return
     */
    public static String encipher(String stringToEncode) {
        if (stringToEncode == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : stringToEncode.toCharArray()) {
            String encoding = ENCODING_MAP.get(Character.toLowerCase(c));

            // Make sure a mapping exists for the given character, throw away non-ascii chars
            if (StringUtils.isNotBlank(encoding)) {
                stringBuilder.append(encoding);
            }
        }

        return stringBuilder.toString();
    }

    /**
     *
     * @param bytesToDecipher
     * @return
     */
    public static byte[] decipher(byte[] bytesToDecipher) {
        if (ArrayUtils.isEmpty(bytesToDecipher)) {
            return new byte[0];
        }

        String decodedString = decipher(new String(bytesToDecipher, Charsets.UTF_8));
        return decodedString.getBytes();
    }

    /**
     *
     * @param stringToDecipher
     * @return
     */
    public static String decipher(String stringToDecipher) {
        if (stringToDecipher == null) {
            return "";
        }

        BiMap<String, Character> inverseEncodingMap = ENCODING_MAP.inverse();
        Iterable<String> pieces = Splitter.fixedLength(5).split(stringToDecipher);
        List<String> baconianStrings = Lists.newArrayList(pieces);

        StringBuilder stringBuilder = new StringBuilder();
        for (String chars : baconianStrings) {
            Character decodedValue = inverseEncodingMap.get(chars);

            if (decodedValue != null) {
                stringBuilder.append(inverseEncodingMap.get(chars));
            }
        }

        return stringBuilder.toString();
    }

    private BaconianCipher() {}

}