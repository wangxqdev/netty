package com.anji_tec.www.netty.client.console.command.impl;

import com.anji_tec.www.netty.client.console.command.ConsoleCommand;
import com.anji_tec.www.netty.protocol.request.SendToGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel ch) {
        System.out.print("发消息给某个群组: ");
        ch.writeAndFlush(new SendToGroupRequestPacket(sc.next(), sc.nextLine()));
    }
}
