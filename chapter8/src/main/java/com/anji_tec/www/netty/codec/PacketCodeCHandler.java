package com.anji_tec.www.netty.codec;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

@ChannelHandler.Sharable
public class PacketCodeCHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodeCHandler INSTANCE = new PacketCodeCHandler();

    private PacketCodeCHandler() {

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = Unpooled.buffer();
        PacketCodeC.INSTANCE.encode(byteBuf, msg);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(PacketCodeC.INSTANCE.decode(msg));
    }
}
