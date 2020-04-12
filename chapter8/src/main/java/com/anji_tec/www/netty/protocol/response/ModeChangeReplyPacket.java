package com.anji_tec.www.netty.protocol.response;

import com.anji_tec.www.netty.protocol.Packet;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ModeChangeReplyPacket extends Packet {

    private String stationNo;

    private String responseCLS;

    public ModeChangeReplyPacket(byte[] bytes) {
        super(bytes);
    }

    @Override
    public void parseMessageBody() {
        setStationNo(decodeField(LEN_STATION_NO));
        setResponseCLS(decodeField(LEN_RESPONSE_CLS));
        setBcc(decodeField(LEN_BCC));
    }

    @Override
    protected String getMessageBody() {
        return new StringBuffer().append(stationNo)
                .append(responseCLS)
                .toString();
    }
}
