package com.anji_tec.www.netty.attribute;

import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
