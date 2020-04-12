package com.anji_tec.www.netty.protocol.response;

import com.anji_tec.www.netty.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArrivalReportPacket extends Packet {

    private String transportKey;

    private String stationNo;

    private String load;

    private String profile;

    private String bcData;

    public ArrivalReportPacket(byte[] bytes) {
        super(bytes);
    }

    @Override
    public void parseMessageBody() {
        setTransportKey(decodeField(LEN_TRANSPORT_KEY));
        setStationNo(decodeField(LEN_STATION_NO));
        setLoad(decodeField(LEN_LOAD));
        setProfile(decodeField(LEN_PROFILE));
        setBcData(decodeField(LEN_BC_DATA));
        setBcc(decodeField(LEN_BCC));
    }

    @Override
    protected String getMessageBody() {
        return new StringBuffer().append(transportKey)
                .append(stationNo)
                .append(load)
                .append(profile)
                .append(bcData)
                .toString();
    }
}
