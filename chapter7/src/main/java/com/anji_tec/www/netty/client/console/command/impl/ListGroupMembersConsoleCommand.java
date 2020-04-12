package com.anji_tec.www.netty.client.console.command.impl;

import com.anji_tec.www.netty.client.console.command.ConsoleCommand;
import com.anji_tec.www.netty.protocol.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel ch) {
        System.out.print("输入 groupId, 获取群成员列表: ");
        ch.writeAndFlush(new ListGroupMembersRequestPacket(sc.nextLine()));
    }
}
