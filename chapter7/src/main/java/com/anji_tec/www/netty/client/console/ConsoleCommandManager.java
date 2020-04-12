package com.anji_tec.www.netty.client.console;

import com.anji_tec.www.netty.client.console.command.ConsoleCommand;
import com.anji_tec.www.netty.client.console.command.impl.CreateGroupConsoleCommand;
import com.anji_tec.www.netty.client.console.command.impl.JoinGroupConsoleCommand;
import com.anji_tec.www.netty.client.console.command.impl.ListGroupMembersConsoleCommand;
import com.anji_tec.www.netty.client.console.command.impl.LogoutConsoleCommand;
import com.anji_tec.www.netty.client.console.command.impl.QuitGroupConsoleCommand;
import com.anji_tec.www.netty.client.console.command.impl.SendToGroupConsoleCommand;
import com.anji_tec.www.netty.client.console.command.impl.SendToUserConsoleCommand;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {

    public static final ConsoleCommandManager INSTANCE = new ConsoleCommandManager();

    private final Map<String, ConsoleCommand> consoleCommandMap;

    private ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put(ConsoleCommand.LOGOUT, new LogoutConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.SEND_TO_USER, new SendToUserConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.CREATE_GROUP, new CreateGroupConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.JOIN_GROUP, new JoinGroupConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.QUIT_GROUP, new QuitGroupConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.LIST_GROUP_MEMBERS, new ListGroupMembersConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.SEND_TO_GROUP, new SendToGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner sc, Channel ch) {
        String command = sc.nextLine();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (null != consoleCommand) {
            consoleCommand.exec(sc, ch);
        } else {
            System.err.println("无法识别[" + command + "]指令, 请重新输入!");
        }
    }
}
