package com.adamchilds.secuur.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 *
 */
public class SecuurArrayUtils {

    /**
     * Circularly shifts the given {@code arrayToShift} by the given {@code shift}.
     *
     * For example,
     *   Input:
     *      arrayToShift --> { 'a', 'b', 'c', 'd', 'e', 'f' }
     *      shift --> 3
     *
     *   Output:
     *      { 'd', 'e', 'f', 'a', 'b', 'c', }
     *
     * @param arrayToShift the array to shift
     * @param shift the amount to shift the array by
     * @return the shifted array
     */
    public static char[] shift(char[] arrayToShift, int shift) {
        if (ArrayUtils.isEmpty(arrayToShift)) {
            return new char[0];
        }

        char[] copy = arrayToShift.clone();
        int offset = Math.abs(arrayToShift.length - shift % arrayToShift.length);

        if (offset > 0) {
            for (int i = 0; i < arrayToShift.length; i++) {
                int j = (i + offset) % arrayToShift.length;
                copy[i] = arrayToShift[j];
            }
        }

        return copy;
    }

}