package com.anji_tec.www.netty.util;

public class BccUtil {

    public static boolean check(String content, String recvBcc) {
        return make(content).equals(recvBcc);
    }

    public static String make(String content) {
        if (content.isEmpty()) {
            return "";
        } else {
            int bcc = 0;
            for (int i = 0; i < content.length(); i++) {
                bcc = bcc ^ content.charAt(i);
            }
            String bccData = Integer.toHexString(bcc);
            if (bccData.length() == 1) {
                bccData = "0" + bccData;
            }
            return bccData.toUpperCase();
        }
    }
}
