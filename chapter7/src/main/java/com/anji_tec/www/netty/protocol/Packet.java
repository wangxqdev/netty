package com.anji_tec.www.netty.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public abstract class Packet {

    private byte version = 1;

    public abstract byte getCommand();
}
