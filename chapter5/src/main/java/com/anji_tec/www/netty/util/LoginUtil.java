package com.anji_tec.www.netty.util;

import com.anji_tec.www.netty.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return null != loginAttr;
    }
}
