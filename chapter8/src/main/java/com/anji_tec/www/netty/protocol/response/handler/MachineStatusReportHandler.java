package com.anji_tec.www.netty.protocol.response.handler;

import com.anji_tec.www.netty.protocol.response.MachineStatusReportPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class MachineStatusReportHandler extends SimpleChannelInboundHandler<MachineStatusReportPacket> {

    public static final MachineStatusReportHandler INSTANCE = new MachineStatusReportHandler();

    private MachineStatusReportHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MachineStatusReportPacket msg) throws Exception {
        // TODO
    }
}
