package com.cottacush.android.libraries.utils;

/**
 * @author Adegoke Obasa <goke@cottacush.com>
 */

public class TextUtils {

    public static String asteriskPhoneNumber(String phoneNumber, int startIndex, int endIndex) {
        String firstPart = phoneNumber.substring(0, startIndex);
        String endPart = phoneNumber.substring(endIndex);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstPart);

        for (int i = 0; i < (endIndex - startIndex); i++) {
            stringBuilder.append("*");
        }
        stringBuilder.append(endPart);
        return stringBuilder.toString();
    }
}
