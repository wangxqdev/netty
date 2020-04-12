package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) {
        String fromUserId = msg.getFromUserId();
        String fromUsername = msg.getFromUsername();
        System.out.println(fromUserId + ": " + fromUsername + " -> " + msg.getMessage());
    }
}
