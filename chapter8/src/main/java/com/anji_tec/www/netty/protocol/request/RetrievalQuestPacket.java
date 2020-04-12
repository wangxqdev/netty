package com.anji_tec.www.netty.protocol.request;

import com.anji_tec.www.netty.protocol.Packet;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RetrievalQuestPacket extends Packet {

    private String transportKey;

    private String srcStationNo;

    private String destStationNo;

    private String locationNo;

    @Override
    protected void parseMessageBody() {
        setTransportKey(decodeField(LEN_TRANSPORT_KEY));
        setSrcStationNo(decodeField(LEN_STATION_NO));
        setDestStationNo(decodeField(LEN_STATION_NO));
        setLocationNo(decodeField(LEN_LOCATION_NO));
    }

    @Override
    protected String getMessageBody() {
        return new StringBuffer().append(transportKey)
                .append(srcStationNo)
                .append(destStationNo)
                .append(locationNo)
                .toString();
    }
}
