package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.MessageRequestPacket;
import com.anji_tec.www.netty.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) {
        System.out.println(new Date() + ": 收到客户端消息: " + msg.getMessage());
        // 发送消息
        ctx.channel().writeAndFlush(new MessageResponsePacket("服务端回复【" + msg.getMessage() + "】"));
    }
}
