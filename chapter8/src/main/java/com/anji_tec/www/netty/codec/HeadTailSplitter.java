package com.anji_tec.www.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class HeadTailSplitter extends ByteToMessageDecoder {

    public static final byte STX = 0x02;

    public static final byte ETX = 0x03;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        for (; ; ) {
            int fromIndex = in.readerIndex();
            int toIndex = in.writerIndex();
            int begin = in.indexOf(fromIndex, toIndex, STX);
            int end = in.indexOf(fromIndex, toIndex, ETX);
            // 丢包
            if (-1 == begin) {
                in.skipBytes(toIndex - fromIndex);
                return;
            } else if (begin > end && end != -1) {
                in.skipBytes(begin);
            }
            // 半包
            else if (-1 == end) {
                in.discardReadBytes();
                return;
            }
            // 正常
            else {
                out.add(in.copy(begin + 1, end - begin - 1));
                in.skipBytes(end - begin + 1);
            }
        }
    }
}