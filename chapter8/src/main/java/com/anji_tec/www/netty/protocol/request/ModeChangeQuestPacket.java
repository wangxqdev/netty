package com.anji_tec.www.netty.protocol.request;

import com.anji_tec.www.netty.protocol.Packet;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ModeChangeQuestPacket extends Packet {

    private String stationNo;

    private String modeChangeCommand;

    @Override
    protected void parseMessageBody() {
        setStationNo(decodeField(LEN_STATION_NO));
        setModeChangeCommand(decodeField(LEN_MODE_CHANGE_COMMAND));
    }

    @Override
    protected String getMessageBody() {
        return new StringBuffer().append(stationNo)
                .append(modeChangeCommand)
                .toString();
    }
}
