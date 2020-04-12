package com.anji_tec.www.netty.server;

import com.anji_tec.www.netty.codec.PacketCodecHandler;
import com.anji_tec.www.netty.codec.Splitter;
import com.anji_tec.www.netty.filter.IMIdleStateHandler;
import com.anji_tec.www.netty.server.handler.AuthHandler;
import com.anji_tec.www.netty.server.handler.HeartBeatRequestHandler;
import com.anji_tec.www.netty.server.handler.IMHandler;
import com.anji_tec.www.netty.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup workers = new NioEventLoopGroup();

        serverBootstrap
                .group(boss, workers)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        // 黏包拆包
                        ch.pipeline().addLast(new Splitter());
                        // 解码编码
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        // 心跳响应
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });
        bind(serverBootstrap, PORT);
    }

    private static void bind(ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功!");
            } else {
                System.out.println("端口[" + port + "]绑定失败!");
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
