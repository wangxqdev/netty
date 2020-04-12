package com.anji_tec.www.netty.serializer;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.serializer.impl.JSONSerializer;

public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    byte[] serialize(Object object);

    <T> T deserialize(Class<? extends Packet> clazz, byte[] bytes);

    byte getSerializerAlgorithm();
}
