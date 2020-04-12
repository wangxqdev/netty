package com.anji_tec.www.netty.protocol.request;

import com.anji_tec.www.netty.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DigitalOutputPacket extends Packet {

    private String stationNo;

    private String lampType;

    private String lampRequest;

    @Override
    protected void parseMessageBody() {
        setStationNo(decodeField(LEN_STATION_NO));
        setLampType(decodeField(LEN_LAMP_TYPE));
        setLampRequest(decodeField(LEN_LAMP_REQUEST));
    }

    @Override
    protected String getMessageBody() {
        return new StringBuffer().append(stationNo)
                .append(lampType)
                .append(lampRequest)
                .toString();
    }
}
