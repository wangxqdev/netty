package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        System.out.println(msg.getCreateGroupUsername() + " 群创建成功, id 为[" + msg.getGroupId() + "], 群里面有: " + msg.getUsernameList());
    }
}
