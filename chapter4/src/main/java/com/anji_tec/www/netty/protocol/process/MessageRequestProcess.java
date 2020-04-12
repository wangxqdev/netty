package com.anji_tec.www.netty.protocol.process;

import com.anji_tec.www.netty.protocol.PacketCodeC;
import com.anji_tec.www.netty.protocol.packet.MessageRequestPacket;
import com.anji_tec.www.netty.protocol.packet.MessageResponsePacket;
import com.anji_tec.www.netty.protocol.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class MessageRequestProcess implements Process {

    @Override
    public void doProcess(ChannelHandlerContext ctx, Packet packet) {
        MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
        // 封装消息响应
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
        // 封装报文
        ByteBuf out = PacketCodeC.getInstance().encode(ctx.alloc(), messageResponsePacket);
        // 发送消息
        ctx.channel().writeAndFlush(out);
    }
}
