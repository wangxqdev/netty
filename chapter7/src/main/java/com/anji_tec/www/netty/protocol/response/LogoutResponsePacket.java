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
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
