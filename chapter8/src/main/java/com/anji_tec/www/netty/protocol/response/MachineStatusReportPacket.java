package com.anji_tec.www.netty.protocol.response;

import com.anji_tec.www.netty.protocol.Packet;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MachineStatusReportPacket extends Packet {

    private String deviceType;

    private String deviceNo;

    private String status;

    private String errorCode;

    public MachineStatusReportPacket(byte[] bytes) {
        super(bytes);
    }

    @Override
    public void parseMessageBody() {
        setDeviceType(decodeField(LEN_DEVICE_TYPE));
        setDeviceNo(decodeField(LEN_DEVICE_NO));
        setStatus(decodeField(LEN_STATUS));
        setErrorCode(decodeField(LEN_ERROR_CODE));
        setBcc(decodeField(LEN_BCC));
    }

    @Override
    protected String getMessageBody() {
        return new StringBuffer().append(deviceType)
                .append(deviceNo)
                .append(status)
                .append(errorCode)
                .toString();
    }
}
