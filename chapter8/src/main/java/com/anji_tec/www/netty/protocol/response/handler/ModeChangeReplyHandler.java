package com.anji_tec.www.netty.protocol.response.handler;

import com.anji_tec.www.netty.protocol.response.ModeChangeReplyPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ModeChangeReplyHandler extends SimpleChannelInboundHandler<ModeChangeReplyPacket> {

    public static final ModeChangeReplyHandler INSTANCE = new ModeChangeReplyHandler();

    private ModeChangeReplyHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ModeChangeReplyPacket msg) throws Exception {
        // TODO
    }
}
