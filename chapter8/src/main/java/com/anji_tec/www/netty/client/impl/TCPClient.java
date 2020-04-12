package com.anji_tec.www.netty.client.impl;

import com.anji_tec.www.netty.client.Client;
import com.anji_tec.www.netty.client.Connector;
import com.anji_tec.www.netty.codec.HeadTailSplitter;
import com.anji_tec.www.netty.codec.PacketCodeCHandler;
import com.anji_tec.www.netty.filter.BccFilter;
import com.anji_tec.www.netty.filter.SeqNoFilter;
import com.anji_tec.www.netty.protocol.response.handler.HeartBeatReportHandler;
import com.anji_tec.www.netty.protocol.response.handler.PacketHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;

public class TCPClient implements Client {

    private String host;

    private int port;

    private int connectTimeoutMills;

    private int idleTimeSeconds;

    private Connector connector;

    @Override
    public Connector init() {
        connector = new TCPConnector.Builder()
                .group(new NioEventLoopGroup())
                .handler(new TCPConnector.HandlerSet() {

                    @Override
                    public ChannelHandler[] handlers() {
                        return new ChannelHandler[]{
                                // 心跳检测
                                new HeartBeatReportHandler(idleTimeSeconds, idleTimeSeconds, 0),
                                // 拆包黏包
                                new HeadTailSplitter(),
                                // 编码解码
                                PacketCodeCHandler.INSTANCE,
                                // Bcc验证
                                BccFilter.INSTANCE,
                                // SeqNo验证
                                SeqNoFilter.INSTANCE,
                                // 业务报文
                                PacketHandler.INSTANCE
                        };
                    }
                })
                .setConnectTimeoutMills(connectTimeoutMills)
                .setSoKeepAlive(true)
                .setTcpNoDelay(true)
                .build();
        connector.setRemoteAddress(host, port);
        connector.setSessionStateListener(new TCPSessionStateListener());
        return connector;
    }

    @Override
    public void connect() {
        connector.connect();
    }
}
