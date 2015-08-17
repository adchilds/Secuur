package com.adamchilds.secuur.util;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public class SecuurStringUtils {

    /**
     * Removes all non-ASCII characters from the given string (even spaces).
     *
     * @param stringToSanitize the {@link String} to sanitize.
     * @return a new {@link String} with all special characters remove and only ASCII values remaining
     */
    public static String removeSpecialCharacters(String stringToSanitize) {
        if (StringUtils.isBlank(stringToSanitize)) {
            return "";
        }

        return stringToSanitize.replaceAll("[^a-zA-Z0-9]", "");
    }

    private SecuurStringUtils() {}

}