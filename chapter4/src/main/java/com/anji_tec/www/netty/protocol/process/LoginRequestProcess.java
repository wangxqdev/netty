package com.anji_tec.www.netty.protocol.process;

import com.anji_tec.www.netty.protocol.PacketCodeC;
import com.anji_tec.www.netty.protocol.packet.LoginRequestPacket;
import com.anji_tec.www.netty.protocol.packet.LoginResponsePacket;
import com.anji_tec.www.netty.protocol.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class LoginRequestProcess implements Process {

    @Override
    public void doProcess(ChannelHandlerContext ctx, Packet packet) {
        LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
        // 封装登陆响应
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (validate(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码验证失败");
        }
        // 封装报文
        ByteBuf out = PacketCodeC.getInstance().encode(ctx.alloc(), loginResponsePacket);
        // 发送消息
        ctx.channel().writeAndFlush(out);
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
