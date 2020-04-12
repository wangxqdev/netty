package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.LoginResponsePacket;
import com.anji_tec.www.netty.session.Session;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) {
        String userId = msg.getUserId();
        String username = msg.getUsername();

        if (msg.isSuccess()) {
            System.out.println("[" + username + "]登录成功，userId 为: " + userId);
            // 由于客户端、服务端处于2个进程，故需要分别维护各自的登录状态
            SessionUtil.bindSession(new Session(userId, username), ctx.channel());
        } else {
            System.out.println("[" + username + "]登录失败，原因：" + msg.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
