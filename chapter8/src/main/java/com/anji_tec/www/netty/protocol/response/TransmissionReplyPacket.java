package com.anji_tec.www.netty.protocol.response;

import com.anji_tec.www.netty.protocol.Packet;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransmissionReplyPacket extends Packet {

    private String transportKey;

    private String responseCLS;

    public TransmissionReplyPacket(byte[] bytes) {
        super(bytes);
    }

    @Override
    public void parseMessageBody() {
        setTransportKey(decodeField(LEN_TRANSPORT_KEY));
        setResponseCLS(decodeField(LEN_RESPONSE_CLS));
        setBcc(decodeField(LEN_BCC));
    }

    @Override
    protected String getMessageBody() {
        return new StringBuffer().append(transportKey)
                .append(responseCLS)
                .toString();
    }
}
