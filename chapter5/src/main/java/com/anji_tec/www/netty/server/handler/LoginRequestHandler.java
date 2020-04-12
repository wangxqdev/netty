package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.LoginRequestPacket;
import com.anji_tec.www.netty.protocol.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) {
        // 封装登陆响应
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (validate(msg)) {
            loginResponsePacket.setSuccess(true);
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码验证失败");
        }
        // 发送消息
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    /**
     * 登陆验证
     *
     * @param loginRequestPacket
     * @return
     */
    private boolean validate(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
