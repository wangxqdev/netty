package com.anji_tec.www.netty.client.handler;

import com.anji_tec.www.netty.protocol.response.LoginResponsePacket;
import com.anji_tec.www.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) {
        if (msg.isSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            System.out.println(new Date() + ": 客户端登录成功");
        } else {
            System.out.println(new Date() + ": 客户端登录失败, 原因: " + msg.getReason());
        }
    }
}
