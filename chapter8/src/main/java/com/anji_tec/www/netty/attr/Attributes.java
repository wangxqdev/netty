package com.anji_tec.www.netty.attr;

import com.anji_tec.www.netty.client.Session;
import io.netty.util.AttributeKey;

import java.util.concurrent.atomic.AtomicInteger;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

    AttributeKey<AtomicInteger> SEQNO = AttributeKey.newInstance("seqNo");
}
