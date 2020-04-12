package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.LoginRequestPacket;
import com.anji_tec.www.netty.protocol.response.LoginResponsePacket;
import com.anji_tec.www.netty.session.Session;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
        loginResponsePacket.setUsername(msg.getUsername());
        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + msg.getUsername() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, msg.getUsername()), ctx.channel());
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            System.out.println(new Date() + ": 登录失败!");
        }
        ctx.writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
