package com.anji_tec.www.netty.client;

import com.anji_tec.www.netty.protocol.PacketCodeC;
import com.anji_tec.www.netty.protocol.packet.MessageRequestPacket;
import com.anji_tec.www.netty.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8000;

    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup child = new NioEventLoopGroup();

        bootstrap
                .group(child)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(final Bootstrap bootstrap, final String host, final int port, final int retry) {
        bootstrap.connect(host, port).addListener(future -> {
           if (future.isSuccess()) {
               System.out.println("连接成功!");
               Channel channel = ((ChannelFuture) future).channel();
               startConsoleThread(channel);
           } else if (0 == retry) {
               System.out.println("重试次数已用完, 放弃连接!");
           } else {
               int order = MAX_RETRY - retry + 1;
               int delay = 1 << order;
               System.err.println(new Date() + ": 连接失败, 第" + order + "次重连……");
               bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
           }
        });
    }

    private static void startConsoleThread(final Channel channel) {
        System.out.println("输入消息发送至服务端: ");
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMessage(line);
                    ByteBuf out = PacketCodeC.getInstance().encode(channel.alloc(), messageRequestPacket);
                    channel.writeAndFlush(out);
                }
            }
        }).start();
    }
}
