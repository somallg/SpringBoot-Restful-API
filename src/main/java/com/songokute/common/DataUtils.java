package com.songokute.common;

/**
 * Created by Admin on 6/18/2016.
 */
public class DataUtils {
    public static boolean isObjectNull(Object o) {
        return o == null;
    }

    public static boolean isStringEmpty(String s) {
        return s == null || "".equals(s.trim());
    }
}
