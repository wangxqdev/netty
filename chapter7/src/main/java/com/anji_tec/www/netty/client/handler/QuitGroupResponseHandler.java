package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("退出群聊[" + msg.getGroupId() + "]成功!");
        } else {
            System.out.println("退出群聊[" + msg.getGroupId() + "]失败!, 原因为: " + msg.getReason());
        }
    }
}
