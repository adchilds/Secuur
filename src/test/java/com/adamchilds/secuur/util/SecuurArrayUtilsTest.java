package com.adamchilds.secuur.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link SecuurArrayUtils} class
 */
public class SecuurArrayUtilsTest {

    @Test
    public void testShift() {
        // Null
        assertArrayEquals(new char[0], SecuurArrayUtils.shift(null, 1));

        // Empty
        assertArrayEquals(new char[0], SecuurArrayUtils.shift(new char[0], 1));

        // Valid
        char[] input = new char[] { 'a', 'b', 'c', 'd', 'e', 'f' };
        char[] expected = new char[] { 'd', 'e', 'f', 'a', 'b', 'c' };
        assertArrayEquals(expected, SecuurArrayUtils.shift(input, 3));

        input = new char[] { 'g', '4', 'y', 'E', ',', 'K' };
        expected = new char[] { 'K', 'g', '4', 'y', 'E', ',' };
        assertArrayEquals(expected, SecuurArrayUtils.shift(input, 1));
    }

}