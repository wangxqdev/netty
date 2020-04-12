package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.protocol.command.Command;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(Command.LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
        handlerMap.put(Command.SEND_TO_USER_REQUEST, SendToUserRequestHandler.INSTANCE);
        handlerMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        handlerMap.put(Command.SEND_TO_GROUP_REQUEST, SendToGroupRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
    }
}
