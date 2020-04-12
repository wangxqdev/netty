package com.anji_tec.www.netty.protocol.response.handler;

import com.anji_tec.www.netty.protocol.response.CompleteReplyPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class CompleteReplyHandler extends SimpleChannelInboundHandler<CompleteReplyPacket> {

    public static final CompleteReplyHandler INSTANCE = new CompleteReplyHandler();

    private CompleteReplyHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CompleteReplyPacket msg) throws Exception {
        // TODO
    }
}
