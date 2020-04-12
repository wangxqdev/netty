package com.anji_tec.www.netty.client.console.command;


import io.netty.channel.Channel;

import java.util.Scanner;

public interface ConsoleCommand {

    String LOGOUT = "Logout";

    String SEND_TO_USER = "SendToUser";

    String CREATE_GROUP = "CreateGroup";

    String JOIN_GROUP = "JoinGroup";

    String QUIT_GROUP = "QuitGroup";

    String LIST_GROUP_MEMBERS = "ListGroupMembers";

    String SEND_TO_GROUP = "SendToGroup";

    void exec(Scanner sc, Channel ch);
}
