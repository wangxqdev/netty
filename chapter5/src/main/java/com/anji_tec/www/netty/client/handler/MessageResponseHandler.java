package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) {
        System.out.println(new Date() + ": 收到服务端的消息: " + msg.getMessage());
    }
}
