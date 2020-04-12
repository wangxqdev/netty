package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.MessageRequestPacket;
import com.anji_tec.www.netty.protocol.response.MessageResponsePacket;
import com.anji_tec.www.netty.session.Session;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) {
        Session session = SessionUtil.getSession(ctx.channel());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUsername(session.getUsername());
        messageResponsePacket.setMessage(msg.getMessage());

        Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());

        if (null != toUserChannel && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println("[" + msg.getToUserId() + "] 不在线, 发送失败!");
        }
    }
}
