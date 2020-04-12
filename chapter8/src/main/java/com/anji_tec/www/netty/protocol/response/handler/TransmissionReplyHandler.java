package com.anji_tec.www.netty.protocol.response.handler;

import com.anji_tec.www.netty.protocol.response.TransmissionReplyPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class TransmissionReplyHandler extends SimpleChannelInboundHandler<TransmissionReplyPacket> {

    public static final TransmissionReplyHandler INSTANCE = new TransmissionReplyHandler();

    private TransmissionReplyHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TransmissionReplyPacket msg) throws Exception {
        // TODO
    }
}
