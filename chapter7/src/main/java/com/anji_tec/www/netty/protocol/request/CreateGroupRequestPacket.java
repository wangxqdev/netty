package com.anji_tec.www.netty.protocol.request;

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
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList = new ArrayList<>();

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
