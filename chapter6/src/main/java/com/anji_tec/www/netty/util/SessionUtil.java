package com.anji_tec.www.netty.util;

import com.anji_tec.www.netty.attribute.Attributes;
import com.anji_tec.www.netty.session.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }
}
