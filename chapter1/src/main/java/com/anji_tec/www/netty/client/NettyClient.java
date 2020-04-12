package com.anji_tec.www.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 客户端启动
 */
public class NettyClient {

    private static final int MAX_RETRY = 5;

    private static Channel channel = null;

    public static void main(String[] args) throws Exception {

        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup child = new NioEventLoopGroup();

        bootstrap
                .group(child)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                    }
                });
        // 连接服务端
        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);

        for (; ; ) {
            if (null != channel && channel.isActive()) {
                channel.writeAndFlush(new Date() + ": Hello world!");
                TimeUnit.MILLISECONDS.sleep(2000);
            }
        }
    }

    /**
     * 断线重连
     *
     * @param bootstrap
     * @param host
     * @param port
     * @param retry     重连次数
     */
    private static void connect(final Bootstrap bootstrap, final String host, final int port, final int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
                channel = ((ChannelFuture) future).channel();
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
}
