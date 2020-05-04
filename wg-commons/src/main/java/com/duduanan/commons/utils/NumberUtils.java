package com.duduanan.commons.utils;

public class NumberUtils {

    public static int getInt(String number) {
        int result = 0;
        try {
            result = Integer.parseInt(number);
        } catch (Exception ex) {
        }
        return result;
    }
}
