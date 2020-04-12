package com.anji_tec.www.netty.util;

import com.anji_tec.www.netty.attr.Attributes;
import io.netty.channel.Channel;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceUtil {

    public static boolean seqNoCheck(Channel channel, String recvSeqNo) {
        if (nextSeqNo(channel).equals(recvSeqNo)) {
            return true;
        }
        getSeqNo(channel).set(Integer.parseInt(recvSeqNo));
        return false;
    }

    public static String nextSeqNo(Channel channel) {
        int nextSeqNo = getSeqNo(channel).incrementAndGet();
        if (nextSeqNo > 9999) {
            nextSeqNo = 1;
        }
        return Integer.toString(nextSeqNo);
    }

    private static AtomicInteger getSeqNo(Channel channel) {
        return channel.attr(Attributes.SEQNO).get();
    }
}
