package com.anji_tec.www.netty.client.console.command.impl;

import com.anji_tec.www.netty.client.console.command.ConsoleCommand;
import com.anji_tec.www.netty.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel ch) {
        System.out.print("输入 groupId, 退出群聊: ");
        ch.writeAndFlush(new QuitGroupRequestPacket(sc.nextLine()));
    }
}
