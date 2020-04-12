package com.anji_tec.www.netty.protocol.response;

import com.anji_tec.www.netty.protocol.Packet;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PickingCompletePacket extends Packet {

    private String transportKey;

    public PickingCompletePacket(byte[] bytes) {
        super(bytes);
    }

    @Override
    public void parseMessageBody() {
        setTransportKey(decodeField(LEN_TRANSPORT_KEY));
        setBcc(decodeField(LEN_BCC));
    }

    @Override
    protected String getMessageBody() {
        return new StringBuffer().append(transportKey)
                .toString();
    }
}
