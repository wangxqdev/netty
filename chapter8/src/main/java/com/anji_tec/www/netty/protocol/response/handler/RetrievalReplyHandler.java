package com.anji_tec.www.netty.protocol.response.handler;

import com.anji_tec.www.netty.protocol.response.RetrievalReplyPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class RetrievalReplyHandler extends SimpleChannelInboundHandler<RetrievalReplyPacket> {

    public static final RetrievalReplyHandler INSTANCE = new RetrievalReplyHandler();

    private RetrievalReplyHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RetrievalReplyPacket msg) throws Exception {
        // TODO
    }
}
