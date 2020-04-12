package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.QuitGroupRequestPacket;
import com.anji_tec.www.netty.protocol.response.QuitGroupResponsePacket;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        if (null != channelGroup) {
            channelGroup.remove(ctx.channel());
            ctx.writeAndFlush(new QuitGroupResponsePacket(groupId, true, null));
        }
    }
}
