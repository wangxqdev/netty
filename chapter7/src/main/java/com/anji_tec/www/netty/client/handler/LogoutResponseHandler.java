package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.LogoutResponsePacket;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
    }
}
