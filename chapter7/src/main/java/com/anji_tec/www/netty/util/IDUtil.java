package com.anji_tec.www.netty.util;

import java.util.UUID;

public class IDUtil {

    public static String randomID() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
