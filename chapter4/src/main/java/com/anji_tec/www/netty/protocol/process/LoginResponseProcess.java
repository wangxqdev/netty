package com.anji_tec.www.netty.protocol.process;

import com.anji_tec.www.netty.protocol.packet.LoginResponsePacket;
import com.anji_tec.www.netty.protocol.packet.Packet;
import com.anji_tec.www.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class LoginResponseProcess implements Process {

    @Override
    public void doProcess(ChannelHandlerContext ctx, Packet packet) {
        LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

        if (loginResponsePacket.isSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            System.out.println(new Date() + ": 客户端登录成功");
        } else {
            System.out.println(new Date() + ": 客户端登录失败, 原因: " + loginResponsePacket.getReason());
        }
    }
}
