package com.anji_tec.www.netty.protocol.response.handler;

import com.anji_tec.www.netty.protocol.response.ModeChangeCompletePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ModeChangeCompleteHandler extends SimpleChannelInboundHandler<ModeChangeCompletePacket> {

    public static final ModeChangeCompleteHandler INSTANCE = new ModeChangeCompleteHandler();

    private ModeChangeCompleteHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ModeChangeCompletePacket msg) throws Exception {
        // TODO
    }
}
