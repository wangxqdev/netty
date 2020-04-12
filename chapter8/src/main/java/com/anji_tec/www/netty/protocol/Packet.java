package com.anji_tec.www.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class Packet implements FieldLength {

    private String seqNo;

    private String id;

    private String sendTime;

    private String bcc;

    protected ByteBuf byteBuf = Unpooled.buffer();

    public Packet() {

    }

    public Packet(byte[] bytes) {
        byteBuf.writeBytes(bytes);
        parseMessage();
    }

    public byte[] getMessage() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes(getMessageExpBcc());
        byteBuf.writeBytes(getBcc().getBytes());
        return byteBuf.array();
    }

    public byte[] getMessageExpBcc() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes(getSeqNo().getBytes());
        byteBuf.writeBytes(getId().getBytes());
        byteBuf.writeBytes(getSendTime().getBytes());
        byteBuf.writeBytes(getMessageBody().getBytes());
        return byteBuf.array();
    }

    protected abstract void parseMessageBody();

    protected abstract String getMessageBody();

    protected String decodeField(int length) {
        return new String(byteBuf.readBytes(length).array());
    }

    private void parseMessage() {
        setSeqNo(decodeField(LEN_SEQ_NO));
        setId(decodeField(LEN_ID));
        setSendTime(decodeField(LEN_SEND_TIME));
        parseMessageBody();
        byteBuf.resetReaderIndex();
    }
}
