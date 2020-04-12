package com.anji_tec.www.netty.attribute;

import com.anji_tec.www.netty.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
