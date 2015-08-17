package com.adamchilds.secuur.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class SecuurStringUtilsTest {

    @Test
    public void testRemoveSpecialCharacters() {
        // Null
        assertEquals("", SecuurStringUtils.removeSpecialCharacters(null));

        // Empty
        assertEquals("", SecuurStringUtils.removeSpecialCharacters(""));
        assertEquals("", SecuurStringUtils.removeSpecialCharacters("    "));

        // Valid
        assertEquals("Thisisatest", SecuurStringUtils.removeSpecialCharacters("This is a test."));
        assertEquals("", SecuurStringUtils.removeSpecialCharacters("`~!@#$%^&*()-_=+[]{};:'\",<.>/?\\|"));
        assertEquals("ThisisAnotherTest", SecuurStringUtils.removeSpecialCharacters("ThisisAnotherTest"));
        assertEquals("0123456789", SecuurStringUtils.removeSpecialCharacters("0123456789"));
        assertEquals("This1sth3l4stt3st", SecuurStringUtils.removeSpecialCharacters("This 1s th3 l4st t3st."));
    }

}