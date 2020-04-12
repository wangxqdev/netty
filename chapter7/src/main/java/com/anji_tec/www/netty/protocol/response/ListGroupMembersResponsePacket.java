package com.anji_tec.www.netty.protocol.response;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<String> usernameList = new ArrayList<>();

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
