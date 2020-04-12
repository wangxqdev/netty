package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.SendToUserRequestPacket;
import com.anji_tec.www.netty.protocol.response.SendToUserResponsePacket;
import com.anji_tec.www.netty.session.Session;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class SendToUserRequestHandler extends SimpleChannelInboundHandler<SendToUserRequestPacket> {

    public static final SendToUserRequestHandler INSTANCE = new SendToUserRequestHandler();

    private SendToUserRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToUserRequestPacket msg) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());

        SendToUserResponsePacket messageResponsePacket = new SendToUserResponsePacket();
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
