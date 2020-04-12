package com.anji_tec.www.netty.server.handler;

import com.anji_tec.www.netty.protocol.request.CreateGroupRequestPacket;
import com.anji_tec.www.netty.protocol.response.CreateGroupResponsePacket;
import com.anji_tec.www.netty.util.IDUtil;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> usernameList = new ArrayList<>();
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        for (String userId : msg.getUserIdList()) {
            Channel channel = SessionUtil.getChannel(userId);
            if (null != channel) {
                channelGroup.add(channel);
                usernameList.add(SessionUtil.getSession(channel).getUsername());
            }
        }

        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        String groupId = IDUtil.randomID();
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setCreateGroupUsername(SessionUtil.getSession(ctx.channel()).getUsername());
        createGroupResponsePacket.setUsernameList(usernameList);
        createGroupResponsePacket.setSuccess(true);

        channelGroup.writeAndFlush(createGroupResponsePacket);
        System.out.println(createGroupResponsePacket.getCreateGroupUsername() + " 群创建成功, id 为[" + groupId + "], 群里面有: " + usernameList);

        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }
}
