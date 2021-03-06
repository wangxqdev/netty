package com.anji_tec.www.netty.protocol.packet;

import lombok.Data;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
