package com.anji_tec.www.netty.protocol.request;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SendToGroupRequestPacket extends Packet {

    private String toGroupId;

    private String message;

    @Override
    public byte getCommand() {
        return Command.SEND_TO_GROUP_REQUEST;
    }
}
