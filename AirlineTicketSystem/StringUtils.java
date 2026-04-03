package airline.utils;

import java.util.Arrays;

public class StringUtils {
    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }

    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static void sortPassengers(String[] names) {
        Arrays.sort(names);
    }
}
