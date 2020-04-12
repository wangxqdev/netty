package com.anji_tec.www.netty.client;

import com.anji_tec.www.netty.client.handler.LoginResponseHandler;
import com.anji_tec.www.netty.client.handler.MessageResponseHandler;
import com.anji_tec.www.netty.codec.PacketDecoder;
import com.anji_tec.www.netty.codec.PacketEncoder;
import com.anji_tec.www.netty.codec.Splitter;
import com.anji_tec.www.netty.protocol.request.LoginRequestPacket;
import com.anji_tec.www.netty.protocol.request.MessageRequestPacket;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
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
                        ch.pipeline().addLast(new Splitter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
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
                bootstrap.config().group().schedule(()-> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner( System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    System.out.println("输入用户名登录: ");
                    String username = sc.nextLine();

                    loginRequestPacket.setUsername(username);
                    loginRequestPacket.setPassword("pwd");

                    channel.writeAndFlush(loginRequestPacket);
                    waitForLoginResponse();
                } else {
                    System.out.println("输入对方用户ID: ");
                    String toUserId = sc.nextLine();
                    System.out.println("输入消息内容: ");
                    String message = sc.nextLine();
                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
                }
            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
