package com.anji_tec.www.netty.protocol.response;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.protocol.command.Command;
import com.anji_tec.www.netty.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SendToGroupResponsePacket extends Packet {

    private String fromGroupId;

    private String message;

    private Session fromSession;

    @Override
    public byte getCommand() {
        return Command.SEND_TO_GROUP_RESPONSE;
    }
}
