package com.anji_tec.www.netty.serializer;

import com.anji_tec.www.netty.serializer.impl.JSONSerializer;

public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    byte[] serialize(Object object);

    <T> T deserialize(Class<T> clazz, byte[] bytes);

    byte getSerializerAlgorithm();
}
