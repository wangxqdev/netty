package com.anji_tec.www.netty.server;

import com.anji_tec.www.netty.codec.PacketDecoder;
import com.anji_tec.www.netty.codec.PacketEncoder;
import com.anji_tec.www.netty.codec.Splitter;
import com.anji_tec.www.netty.server.handler.AuthHandler;
import com.anji_tec.www.netty.server.handler.LoginRequestHandler;
import com.anji_tec.www.netty.server.handler.MessageRequestHandler;
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

        NioEventLoopGroup parent = new NioEventLoopGroup();
        NioEventLoopGroup child = new NioEventLoopGroup();

        serverBootstrap
                .group(parent, child)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new Splitter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });

        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
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
