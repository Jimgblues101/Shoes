package za.ac.cput.util;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Helper.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 24-Aug-24
 */

public class Helper {
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isDoubleNullOrEmpty(Double s) {
        return s == null || s.isNaN();
    }

    public static boolean isNullOrEmpty(Integer i) {
        return i == null;
    }

    public static boolean isNullOrEmpty(List<Object> s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNullOrEmpty(Object s) {
        return s == null;
    }

    public static boolean isEmailValid(String email, String regex) {
        return Pattern.compile(regex)
                .matcher(email)
                .matches();
    }

    public static boolean isNullOrEmpty(Long l) {
        return l == null || l.describeConstable().isEmpty();
    }
}
