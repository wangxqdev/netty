package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.SendToGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SendToGroupResponseHandler extends SimpleChannelInboundHandler<SendToGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupResponsePacket msg) throws Exception {
        System.out.println("收到群聊[" + msg.getFromGroupId() + "]中" + msg.getFromSession() + "发来的消息: " + msg.getMessage());
    }
}
