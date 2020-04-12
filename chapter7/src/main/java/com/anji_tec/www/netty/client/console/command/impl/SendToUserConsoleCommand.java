package com.anji_tec.www.netty.client.console.command.impl;

import com.anji_tec.www.netty.client.console.command.ConsoleCommand;
import com.anji_tec.www.netty.protocol.request.SendToUserRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel ch) {
        System.out.print("发消息给某个用户: ");
        ch.writeAndFlush(new SendToUserRequestPacket(sc.next(), sc.nextLine()));
    }
}
