package com.anji_tec.www.netty.codec;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) {
        PacketCodeC.getInstance().encode(out, msg);
    }
}
