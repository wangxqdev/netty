package com.anji_tec.www.netty.protocol;

import com.anji_tec.www.netty.protocol.command.Command;
import com.anji_tec.www.netty.protocol.request.LoginRequestPacket;
import com.anji_tec.www.netty.protocol.request.MessageRequestPacket;
import com.anji_tec.www.netty.protocol.response.LoginResponsePacket;
import com.anji_tec.www.netty.protocol.response.MessageResponsePacket;
import com.anji_tec.www.netty.serializer.Serializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = Serializer.DEFAULT;
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 魔数
        byteBuf.writeInt(MAGIC_NUMBER);
        // 版本号
        byteBuf.writeByte(packet.getVersion());
        // 序列化
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        // 指令号
        byteBuf.writeByte(packet.getCommand());
        // 长度
        byteBuf.writeInt(bytes.length);
        // 内容
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 跳过序列化
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 长度
        int length = byteBuf.readInt();
        // 内容
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> packetType = getPacketType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (packetType != null && serializer != null) {
            return serializer.deserialize(packetType, bytes);
        }
        return null;
    }

    private Class<? extends Packet> getPacketType(byte command) {
        return packetTypeMap.get(command);
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }
}
