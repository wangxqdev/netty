package com.anji_tec.www.netty.protocol.response.handler;

import com.anji_tec.www.netty.protocol.response.PickingCompletePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class PickingCompleteHandler extends SimpleChannelInboundHandler<PickingCompletePacket> {

    public static final PickingCompleteHandler INSTANCE = new PickingCompleteHandler();

    private PickingCompleteHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PickingCompletePacket msg) throws Exception {
        // TODO
    }
}
