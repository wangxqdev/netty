package com.anji_tec.www.netty.protocol.request;

import com.anji_tec.www.netty.protocol.Packet;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HeartBeatPacket extends Packet {

    public HeartBeatPacket() {

    }

    @Override
    protected void parseMessageBody() {
        // do nothing
    }

    @Override
    protected String getMessageBody() {
        return "";
    }

    public static void main(String[] args) {
        System.out.println("".getBytes());
    }
}
