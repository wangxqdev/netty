package com.anji_tec.www.netty.protocol.response;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.protocol.command.Command;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class HeartBeatResponsePacket extends Packet {

    @Override
    public byte getCommand() {
        return Command.HEART_BEAT_RESPONSE;
    }
}
