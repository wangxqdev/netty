package com.anji_tec.www.netty.protocol.response;

import com.anji_tec.www.netty.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CompleteReplyPacket extends Packet {

    private String transportKey;

    private String completeCLS;

    public CompleteReplyPacket(byte[] bytes) {
        super(bytes);
    }

    @Override
    public void parseMessageBody() {
        setTransportKey(decodeField(LEN_TRANSPORT_KEY));
        setCompleteCLS(decodeField(LEN_COMPLETE_CLS));
        setBcc(decodeField(LEN_BCC));
    }

    @Override
    protected String getMessageBody() {
        return new StringBuffer().append(transportKey)
                .append(completeCLS)
                .toString();
    }
}
