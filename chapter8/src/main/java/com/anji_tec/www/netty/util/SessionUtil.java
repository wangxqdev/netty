package com.anji_tec.www.netty.util;

import com.anji_tec.www.netty.attr.Attributes;
import com.anji_tec.www.netty.client.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SessionUtil {

    private static Map<String, Channel> hostChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        hostChannelMap.put(session.getHost(), channel);
        channel.attr(Attributes.SESSION).set(session);
        channel.attr(Attributes.SEQNO).set(new AtomicInteger());
    }

    public static void unbindSession(Channel channel) {
        if (hasConnected(channel)) {
            Session session = getSession(channel);
            hostChannelMap.remove(session.getHost());
            channel.attr(Attributes.SESSION).set(null);
            channel.attr(Attributes.SEQNO).set(null);
        }
    }

    public static boolean hasConnected(String host) {
        Channel channel = hostChannelMap.get(host);
        if (null != channel) {
            hasConnected(channel);
        }
        return false;
    }

    public static boolean hasConnected(Channel channel) {
        if (channel.hasAttr(Attributes.SESSION) && channel.isActive()) {
            return true;
        }
        return false;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String host) {
        return hostChannelMap.get(host);
    }
}
