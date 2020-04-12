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
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
