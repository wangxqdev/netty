package com.anji_tec.www.netty.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.serializer.Serializer;
import com.anji_tec.www.netty.serializer.SerializerAlgorithm;

public class JSONSerializer implements Serializer {

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<? extends Packet> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }
}
