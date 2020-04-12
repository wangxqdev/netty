package com.anji_tec.www.netty.protocol;

import com.anji_tec.www.netty.codec.HeadTailSplitter;
import com.anji_tec.www.netty.protocol.request.DigitalOutputPacket;
import com.anji_tec.www.netty.protocol.request.HeartBeatPacket;
import com.anji_tec.www.netty.protocol.request.ModeChangeQuestPacket;
import com.anji_tec.www.netty.protocol.request.RetrievalQuestPacket;
import com.anji_tec.www.netty.protocol.request.TransmissionQuestPacket;
import com.anji_tec.www.netty.protocol.response.ArrivalReportPacket;
import com.anji_tec.www.netty.protocol.response.CompleteReplyPacket;
import com.anji_tec.www.netty.protocol.response.MachineStatusReportPacket;
import com.anji_tec.www.netty.protocol.response.ModeChangeCompletePacket;
import com.anji_tec.www.netty.protocol.response.ModeChangeReplyPacket;
import com.anji_tec.www.netty.protocol.response.PickingCompletePacket;
import com.anji_tec.www.netty.protocol.response.RetrievalReplyPacket;
import com.anji_tec.www.netty.protocol.response.TransmissionReplyPacket;
import com.anji_tec.www.netty.serializer.Serializer;
import com.anji_tec.www.netty.serializer.SerializerAlgorithm;
import com.anji_tec.www.netty.serializer.impl.BinarySerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static Map<String, Class<? extends Packet>> packetTypeMap = new HashMap<>();

    private static Map<Byte, Serializer> serializerMap = new HashMap<>();

    private PacketCodeC() {
        packetTypeMap.put(Id.ARRIVAL_REPORT, ArrivalReportPacket.class);
        packetTypeMap.put(Id.TRANSMISSION_QUEST, TransmissionQuestPacket.class);
        packetTypeMap.put(Id.TRANSMISSION_REPLY, TransmissionReplyPacket.class);
        packetTypeMap.put(Id.PICKING_COMPLETE, PickingCompletePacket.class);
        packetTypeMap.put(Id.COMPLETE_REPLY, CompleteReplyPacket.class);
        packetTypeMap.put(Id.DIGITAL_OUTPUT, DigitalOutputPacket.class);
        packetTypeMap.put(Id.RETRIEVAL_QUEST, RetrievalQuestPacket.class);
        packetTypeMap.put(Id.RETRIEVAL_REPLY, RetrievalReplyPacket.class);
        packetTypeMap.put(Id.MODE_CHANGE_QUEST, ModeChangeQuestPacket.class);
        packetTypeMap.put(Id.MODE_CHANGE_REPLY, ModeChangeReplyPacket.class);
        packetTypeMap.put(Id.MODE_CHANGE_COMPLETE, ModeChangeCompletePacket.class);
        packetTypeMap.put(Id.MACHINE_STATUS_REPORT, MachineStatusReportPacket.class);
        packetTypeMap.put(Id.HEART_BEAT, HeartBeatPacket.class);

        serializerMap.put(SerializerAlgorithm.BINARY, new BinarySerializer());
    }


    public void encode(ByteBuf byteBuf, Packet packet) {
        byte[] bytes = getSerializer(SerializerAlgorithm.BINARY).serialize(packet);
        byteBuf.writeByte(HeadTailSplitter.STX);
        byteBuf.writeBytes(bytes);
        byteBuf.writeByte(HeadTailSplitter.ETX);
    }

    public Packet decode(ByteBuf byteBuf) {
        // ID
        String id = byteBuf.copy(FieldLength.LEN_SEQ_NO - 1, FieldLength.LEN_ID).toString();
        // 报文
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);

        Serializer serializer = getSerializer(SerializerAlgorithm.BINARY);
        Class<? extends Packet> packetType = getPacketType(id);

        if (null != serializer && null != packetType) {
            return serializer.deserialize(packetType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return serializerMap.get(serializerAlgorithm);
    }

    private Class<? extends Packet> getPacketType(String id) {
        return packetTypeMap.get(id);
    }
}
