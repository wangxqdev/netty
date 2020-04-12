package com.anji_tec.www.netty.client.console.command.impl;

import com.anji_tec.www.netty.client.console.command.ConsoleCommand;
import com.anji_tec.www.netty.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel ch) {
        System.out.print("输入 groupId, 加入群聊: ");
        ch.writeAndFlush(new JoinGroupRequestPacket(sc.nextLine()));
    }
}
