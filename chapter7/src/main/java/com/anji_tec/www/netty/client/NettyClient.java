package com.anji_tec.www.netty.client;

import com.anji_tec.www.netty.client.console.ConsoleCommandManager;
import com.anji_tec.www.netty.client.console.command.impl.LoginConsoleCommand;
import com.anji_tec.www.netty.client.handler.CreateGroupResponseHandler;
import com.anji_tec.www.netty.client.handler.HeartBeatTimerHandler;
import com.anji_tec.www.netty.client.handler.JoinGroupResponseHandler;
import com.anji_tec.www.netty.client.handler.ListGroupMembersResponseHandler;
import com.anji_tec.www.netty.client.handler.LoginResponseHandler;
import com.anji_tec.www.netty.client.handler.LogoutResponseHandler;
import com.anji_tec.www.netty.client.handler.QuitGroupResponseHandler;
import com.anji_tec.www.netty.client.handler.SendToGroupResponseHandler;
import com.anji_tec.www.netty.client.handler.SendToUserResponseHandler;
import com.anji_tec.www.netty.codec.PacketDecoder;
import com.anji_tec.www.netty.codec.PacketEncoder;
import com.anji_tec.www.netty.codec.Splitter;
import com.anji_tec.www.netty.filter.IMIdleStateHandler;
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

        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap
                .group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        // 黏包拆包
                        ch.pipeline().addLast(new Splitter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new LogoutResponseHandler());
                        ch.pipeline().addLast(new SendToUserResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                        ch.pipeline().addLast(new SendToGroupResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                        ch.pipeline().addLast(new HeartBeatTimerHandler());
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

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (SessionUtil.hasLogin(channel)) {
                    ConsoleCommandManager.INSTANCE.exec(sc, channel);
                } else {
                    new LoginConsoleCommand().exec(sc, channel);
                }
            }
        }).start();
    }
}
