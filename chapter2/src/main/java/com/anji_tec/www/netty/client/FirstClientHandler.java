package com.anji_tec.www.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端写出数据");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(new Date() + ": 客户端读到数据 -> " + in.toString(Charset.forName("UTF-8")));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "Hello world!".getBytes(Charset.forName("UTF-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }
}
