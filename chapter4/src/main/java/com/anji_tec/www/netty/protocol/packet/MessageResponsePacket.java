package com.anji_tec.www.netty.protocol.packet;

import lombok.Data;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
