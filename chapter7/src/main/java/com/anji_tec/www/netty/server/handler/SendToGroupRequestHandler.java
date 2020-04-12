package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.SendToGroupRequestPacket;
import com.anji_tec.www.netty.protocol.response.SendToGroupResponsePacket;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class SendToGroupRequestHandler extends SimpleChannelInboundHandler<SendToGroupRequestPacket> {

    public static final SendToGroupRequestHandler INSTANCE = new SendToGroupRequestHandler();

    private SendToGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupRequestPacket msg) throws Exception {
        String groupId = msg.getToGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        for (Channel channel: channelGroup) {
            if (!ctx.channel().equals(channel)) {
                channel.writeAndFlush(new SendToGroupResponsePacket(groupId, msg.getMessage(), SessionUtil.getSession(ctx.channel())));
            }
        }
    }
}
