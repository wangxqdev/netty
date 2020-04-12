package com.anji_tec.www.netty.serializer.impl;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.serializer.Serializer;
import com.anji_tec.www.netty.serializer.SerializerAlgorithm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;

public class BinarySerializer implements Serializer {

    private Log log = LogFactory.getLog(getClass());

    @Override
    public byte[] serialize(Packet packet) {
        return packet.getMessage();
    }

    @Override
    public <T extends Packet> T deserialize(Class<T> clazz, byte[] bytes) {
        try {
            Constructor<T> cons = clazz.getConstructor(byte[].class);
            T packet = cons.newInstance(bytes);
            return packet;
        } catch (Exception e) {
            log.error("Unexpected error has occurred", e);
        }
        return null;
    }

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.BINARY;
    }
}
