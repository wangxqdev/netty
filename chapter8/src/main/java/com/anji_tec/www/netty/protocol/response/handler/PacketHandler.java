package com.anji_tec.www.netty.protocol.response.handler;

import com.anji_tec.www.netty.protocol.Id;
import com.anji_tec.www.netty.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable
public class PacketHandler extends SimpleChannelInboundHandler<Packet> {

    public static final PacketHandler INSTANCE = new PacketHandler();

    private Map<String, SimpleChannelInboundHandler<? extends Packet>> handlerMap = new HashMap<>();

    private PacketHandler() {
        handlerMap.put(Id.ARRIVAL_REPORT, ArrivalReportHandler.INSTANCE);
        handlerMap.put(Id.TRANSMISSION_REPLY, TransmissionReplyHandler.INSTANCE);
        handlerMap.put(Id.PICKING_COMPLETE, PickingCompleteHandler.INSTANCE);
        handlerMap.put(Id.COMPLETE_REPLY, CompleteReplyHandler.INSTANCE);
        handlerMap.put(Id.RETRIEVAL_REPLY, RetrievalReplyHandler.INSTANCE);
        handlerMap.put(Id.MODE_CHANGE_REPLY, ModeChangeReplyHandler.INSTANCE);
        handlerMap.put(Id.MODE_CHANGE_COMPLETE, ModeChangeCompleteHandler.INSTANCE);
        handlerMap.put(Id.MACHINE_STATUS_REPORT, MachineStatusReportHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        handlerMap.get(msg.getId()).channelRead(ctx, msg);
    }
}
