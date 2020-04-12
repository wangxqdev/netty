package com.anji_tec.www.netty.protocol.response;

import com.anji_tec.www.netty.protocol.Packet;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ModeChangeCompletePacket extends Packet {

    private String stationNo;

    private String completeMode;

    public ModeChangeCompletePacket(byte[] bytes) {
        super(bytes);
    }

    @Override
    public void parseMessageBody() {
        setStationNo(decodeField(LEN_STATION_NO));
        setCompleteMode(decodeField(LEN_COMPLETE_MODE));
        setBcc(decodeField(LEN_BCC));
    }

    @Override
    protected String getMessageBody() {
        return new StringBuffer().append(stationNo)
                .append(completeMode)
                .toString();
    }
}
