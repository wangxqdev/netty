package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("加入群聊[" + msg.getGroupId() + "]成功!");
        } else {
            System.out.println("加入群聊[" + msg.getGroupId() + "]失败!, 原因为: " + msg.getReason());
        }
    }
}
