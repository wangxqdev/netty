package com.anji_tec.www.netty.protocol.response.handler;

import com.anji_tec.www.netty.protocol.response.ArrivalReportPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ArrivalReportHandler extends SimpleChannelInboundHandler<ArrivalReportPacket> {

    public static final ArrivalReportHandler INSTANCE = new ArrivalReportHandler();

    private ArrivalReportHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ArrivalReportPacket msg) throws Exception {
        // TODO
    }
}
