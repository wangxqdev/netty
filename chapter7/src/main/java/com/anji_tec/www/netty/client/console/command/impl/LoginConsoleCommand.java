package com.anji_tec.www.netty.client.console.command.impl;

import com.anji_tec.www.netty.client.console.command.ConsoleCommand;
import com.anji_tec.www.netty.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel ch) {
        System.out.print("输入用户名登录: ");
        ch.writeAndFlush(new LoginRequestPacket(null, sc.nextLine(), "pwd"));
        waitForLoginResponse();
    }

    private void waitForLoginResponse() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
