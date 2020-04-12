package com.anji_tec.www.netty.protocol.request;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.protocol.command.Command;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class LogoutRequestPacket extends Packet {

    @Override
    public byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
