package com.anji_tec.www.netty.protocol;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class Packet {

    private byte version = 1;

    public abstract byte getCommand();
}
