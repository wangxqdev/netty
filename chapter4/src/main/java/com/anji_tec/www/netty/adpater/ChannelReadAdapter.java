package com.anji_tec.www.netty.adpater;

import com.anji_tec.www.netty.protocol.PacketCodeC;
import com.anji_tec.www.netty.protocol.packet.Command;
import com.anji_tec.www.netty.protocol.packet.Packet;
import com.anji_tec.www.netty.protocol.process.LoginRequestProcess;
import com.anji_tec.www.netty.protocol.process.LoginResponseProcess;
import com.anji_tec.www.netty.protocol.process.MessageRequestProcess;
import com.anji_tec.www.netty.protocol.process.MessageResponseProcess;
import com.anji_tec.www.netty.protocol.process.Process;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

public class ChannelReadAdapter extends ChannelInboundHandlerAdapter {

    private static final Map<Byte, Class<? extends Process>> packetProcessMap;

    static {
        packetProcessMap = new HashMap<>();
        // 请求消息处理
        packetProcessMap.put(Command.LOGIN_REQUEST, LoginRequestProcess.class);
        packetProcessMap.put(Command.MESSAGE_REQUEST, MessageRequestProcess.class);
        // 相应消息处理
        packetProcessMap.put(Command.LOGIN_RESPONSE, LoginResponseProcess.class);
        packetProcessMap.put(Command.MESSAGE_RESPONSE, MessageResponseProcess.class);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 接收消息
        ByteBuf in = (ByteBuf) msg;
        // 解码报文
        Packet packet = PacketCodeC.getInstance().decode(in);
        // 策略模式(表驱动)
        packetProcessMap.forEach((command, clazz)-> {
            if (command.equals(packet.getCommand())) {
                try {
                    clazz.newInstance().doProcess(ctx, packet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
