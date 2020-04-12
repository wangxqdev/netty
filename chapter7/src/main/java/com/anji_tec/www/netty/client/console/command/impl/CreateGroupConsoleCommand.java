package com.anji_tec.www.netty.client.console.command.impl;

import com.anji_tec.www.netty.client.console.command.ConsoleCommand;
import com.anji_tec.www.netty.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITTER = ",";

    @Override
    public void exec(Scanner sc, Channel ch) {
        System.out.print("[拉人群聊] 输入 userId 列表, userId 之间英文逗号隔开: ");
        ch.writeAndFlush(new CreateGroupRequestPacket(Arrays.asList(sc.nextLine().split(USER_ID_SPLITTER))));
    }
}
