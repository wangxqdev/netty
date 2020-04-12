package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.JoinGroupRequestPacket;
import com.anji_tec.www.netty.protocol.response.JoinGroupResponsePacket;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        if (null != channelGroup) {
            Channel channel = ctx.channel();
            channelGroup.add(channel);
            channel.writeAndFlush(new JoinGroupResponsePacket(groupId, true, null));
        }
    }
}
