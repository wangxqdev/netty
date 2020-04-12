package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.LogoutRequestPacket;
import com.anji_tec.www.netty.protocol.response.LogoutResponsePacket;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
        ctx.writeAndFlush(new LogoutResponsePacket(true, null));
    }
}
