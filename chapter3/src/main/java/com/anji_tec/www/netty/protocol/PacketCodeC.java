package com.anji_tec.www.netty.protocol;

import com.anji_tec.www.netty.protocol.request.LoginRequestPacket;
import com.anji_tec.www.netty.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        serializerMap.put(Serializer.DEFAULT.getSerializerAlgorithm(), Serializer.DEFAULT);
    }

    public ByteBuf encode(Packet packet) {
        ByteBuf out = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 魔数:4位
        out.writeInt(MAGIC_NUMBER);
        // 版本号:1位
        out.writeByte(packet.getVersion());
        // 序列化算法:1位
        out.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        // 指令:1位
        out.writeByte(packet.getCommand());
        // 数据长度:4位
        out.writeInt(bytes.length);
        // 数据
        out.writeBytes(bytes);

        return out;
    }

    public Packet decode(ByteBuf in) {
        // 魔数
        in.skipBytes(4);
        // 版本号
        in.skipBytes(1);
        // 序列化算法
        byte serializeAlgorithm = in.readByte();
        // 指令
        byte command = in.readByte();
        // 数据长度
        int length = in.readInt();
        // 数据
        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        Class<? extends Packet> packetType = getPacketType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (null != packetType && null != serializer) {
            return serializer.deserialize(packetType, bytes);
        }

        return null;
    }

    private Class<? extends Packet> getPacketType(byte command) {
        return packetTypeMap.get(command);
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return serializerMap.get(serializerAlgorithm);
    }
}
