package org.dnlkk.utils;

public class StringUtils {
    private StringUtils() {}

    public static String truncateAndAppendEllipsis(String input, int maxLength) {
        if (input.length() <= maxLength) {
            return input;
        } else {
            return input.substring(0, maxLength) + "...";
        }
    }
}
