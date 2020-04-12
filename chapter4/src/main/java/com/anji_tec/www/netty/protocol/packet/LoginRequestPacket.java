package com.anji_tec.www.netty.protocol.packet;

import lombok.Data;

@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
