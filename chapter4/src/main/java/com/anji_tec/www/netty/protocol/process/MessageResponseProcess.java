package com.anji_tec.www.netty.protocol.process;

import com.anji_tec.www.netty.protocol.packet.MessageResponsePacket;
import com.anji_tec.www.netty.protocol.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class MessageResponseProcess implements Process {

    @Override
    public void doProcess(ChannelHandlerContext ctx, Packet packet) {
        MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
        System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
    }
}
