package com.anji_tec.www.netty.protocol;

import com.anji_tec.www.netty.protocol.command.Command;
import com.anji_tec.www.netty.protocol.request.CreateGroupRequestPacket;
import com.anji_tec.www.netty.protocol.request.HeartBeatRequestPacket;
import com.anji_tec.www.netty.protocol.request.JoinGroupRequestPacket;
import com.anji_tec.www.netty.protocol.request.ListGroupMembersRequestPacket;
import com.anji_tec.www.netty.protocol.request.LoginRequestPacket;
import com.anji_tec.www.netty.protocol.request.LogoutRequestPacket;
import com.anji_tec.www.netty.protocol.request.SendToGroupRequestPacket;
import com.anji_tec.www.netty.protocol.request.SendToUserRequestPacket;
import com.anji_tec.www.netty.protocol.request.QuitGroupRequestPacket;
import com.anji_tec.www.netty.protocol.response.CreateGroupResponsePacket;
import com.anji_tec.www.netty.protocol.response.HeartBeatResponsePacket;
import com.anji_tec.www.netty.protocol.response.JoinGroupResponsePacket;
import com.anji_tec.www.netty.protocol.response.ListGroupMembersResponsePacket;
import com.anji_tec.www.netty.protocol.response.LoginResponsePacket;
import com.anji_tec.www.netty.protocol.response.LogoutResponsePacket;
import com.anji_tec.www.netty.protocol.response.SendToGroupResponsePacket;
import com.anji_tec.www.netty.protocol.response.SendToUserResponsePacket;
import com.anji_tec.www.netty.protocol.response.QuitGroupResponsePacket;
import com.anji_tec.www.netty.serializer.Serializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static Map<Byte, Class<? extends Packet>> packetTypeMap;

    private static Map<Byte, Serializer> serializerMap;

    private PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(Command.SEND_TO_USER_REQUEST, SendToUserRequestPacket.class);
        packetTypeMap.put(Command.SEND_TO_USER_RESPONSE, SendToUserResponsePacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        packetTypeMap.put(Command.SEND_TO_GROUP_REQUEST, SendToGroupRequestPacket.class);
        packetTypeMap.put(Command.SEND_TO_GROUP_RESPONSE, SendToGroupResponsePacket.class);
        packetTypeMap.put(Command.HEART_BEAT_REQUEST, HeartBeatRequestPacket.class);
        packetTypeMap.put(Command.HEART_BEAT_RESPONSE, HeartBeatResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = Serializer.DEFAULT;
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 模数
        byteBuf.writeInt(MAGIC_NUMBER);
        // 版本号
        byteBuf.writeByte(packet.getVersion());
        // 序列化算法
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        // 指令号
        byteBuf.writeByte(packet.getCommand());
        // 报文长度
        byteBuf.writeInt(bytes.length);
        // 报文内容
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过模数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法
        byte serializerAlgorithm = byteBuf.readByte();
        // 指令号
        byte command = byteBuf.readByte();
        // 报文长度
        int length = byteBuf.readInt();
        // 报文内容
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Serializer serializer = getSerializer(serializerAlgorithm);
        Class<? extends Packet> packetType = getPacketType(command);

        if (null != serializer && null != packetType) {
            return serializer.deserialize(packetType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return serializerMap.get(serializerAlgorithm);
    }

    private Class<? extends Packet> getPacketType(byte command) {
        return packetTypeMap.get(command);
    }
}
