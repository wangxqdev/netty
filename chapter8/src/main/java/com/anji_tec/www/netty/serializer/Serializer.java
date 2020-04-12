package com.anji_tec.www.netty.serializer;

import com.anji_tec.www.netty.protocol.Packet;

public interface Serializer {

    byte[] serialize(Packet packet);

    <T extends Packet> T deserialize(Class<T> clazz, byte[] bytes);

    byte getSerializerAlgorithm();
}
