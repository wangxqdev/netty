package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.SendToUserResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SendToUserResponseHandler extends SimpleChannelInboundHandler<SendToUserResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToUserResponsePacket msg) {
        System.out.println(msg.getFromUserId() + ": " + msg.getFromUsername() + " -> " + msg.getMessage());
    }
}
