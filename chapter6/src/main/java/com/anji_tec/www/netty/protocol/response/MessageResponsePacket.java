package com.anji_tec.www.netty.protocol.response;

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
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUsername;

    private String message;

    @Override
    public byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
