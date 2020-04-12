package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.ListGroupMembersRequestPacket;
import com.anji_tec.www.netty.protocol.response.ListGroupMembersResponsePacket;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        List<String> usernameList = new ArrayList<>();

        for (Channel channel : channelGroup) {
            usernameList.add(SessionUtil.getSession(channel).getUsername());
        }
        ctx.writeAndFlush(new ListGroupMembersResponsePacket(groupId, usernameList));
    }
}
