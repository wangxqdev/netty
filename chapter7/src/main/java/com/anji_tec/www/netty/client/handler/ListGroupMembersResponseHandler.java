package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket msg) throws Exception {
        System.out.println("群聊[" + msg.getGroupId() + "]中的人包括: " + msg.getUsernameList());
    }
}
