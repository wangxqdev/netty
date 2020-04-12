package com.anji_tec.www.netty.protocol.process;

import com.anji_tec.www.netty.protocol.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public interface Process {

    void doProcess(ChannelHandlerContext ctx, Packet packet);
}
