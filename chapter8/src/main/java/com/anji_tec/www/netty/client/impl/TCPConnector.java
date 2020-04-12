package com.anji_tec.www.netty.client.impl;

import com.anji_tec.www.netty.client.Connector;
import com.anji_tec.www.netty.client.Session;
import com.anji_tec.www.netty.client.SessionStateListener;
import com.anji_tec.www.netty.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TCPConnector implements Connector {

    private Log log = LogFactory.getLog(getClass());

    /**
     * 连接器
     */
    private Bootstrap bootstrap;

    /**
     * 重连计数
     */
    private AtomicInteger retryCount;

    /**
     * 连接属性
     */
    private Session session;

    /**
     * 通信状态
     */
    private SessionStateListener listener;

    private TCPConnector(final Builder builder) {
        retryCount = new AtomicInteger(0);
        bootstrap = builder.bootstrap;
        bootstrap.handler(new ChannelInitializer() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new ChannelDisconnectHandler());
                ch.pipeline().addLast(builder.handlerSets.handlers());
            }
        });
    }

    @Override
    public void setRemoteAddress(String host, int port) {
        session = new TCPSession(host, port);
    }

    @Override
    public void setSessionStateListener(SessionStateListener listener) {
        this.listener = listener;
    }

    @Override
    public void connect() {
        if (null == session) {
            log.warn("Please call setRemoteAddress(String, int) first");
            return;
        }
        connect(session.getHost(), session.getPort());
    }

    @Override
    public void connect(String host, int port) {
        if (!SessionUtil.hasConnected(host)) {
            if (null == session) {
                session = new TCPSession(host, port);
            }
            log.info("Socket connect : host = " + session.getHost() + ":" + session.getPort());
            bootstrap.connect(session.getHost(), session.getPort()).addListener(future -> {
                if (future.isSuccess()) {
                    retryCount.set(0);
                    SessionUtil.bindSession(session, ((ChannelFuture) future).channel());
                    if (null != listener) {
                        listener.onConnectSuccess(session);
                    }
                } else {
                    log.info("Disconnected interval retry count = " + retryCount.incrementAndGet());
                    TimeUnit.MILLISECONDS.sleep(1 << retryCount.get());
                    if (null != listener) {
                        listener.onConnectFailed(session);
                    }
                    // 重连
                    connect();
                }
            });
        }
    }

    /**
     * 构造器
     */
    public static class Builder {

        private Bootstrap bootstrap = new Bootstrap();

        private HandlerSet handlerSets;

        private int connectTimeoutMills;

        private boolean isSoKeepAlive;

        private boolean isTcpNoDelay;

        public Builder group(EventLoopGroup eventLoopGroup) {
            Builder.this.bootstrap.group(eventLoopGroup);
            return Builder.this;
        }

        public Builder handler(HandlerSet handlers) {
            Builder.this.handlerSets = handlers;
            return Builder.this;
        }

        public Builder setConnectTimeoutMills(int connectTimeoutMills) {
            Builder.this.connectTimeoutMills = connectTimeoutMills;
            return Builder.this;
        }

        public Builder setSoKeepAlive(boolean isSoKeepAlive) {
            Builder.this.isSoKeepAlive = isSoKeepAlive;
            return Builder.this;
        }

        public Builder setTcpNoDelay(boolean isTcpNoDelay) {
            Builder.this.isTcpNoDelay = isTcpNoDelay;
            return Builder.this;
        }

        public TCPConnector build() {
            bootstrap.channel(NioSocketChannel.class)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeoutMills)
                    .option(ChannelOption.SO_KEEPALIVE, isSoKeepAlive)
                    .option(ChannelOption.TCP_NODELAY, isTcpNoDelay);
            return new TCPConnector(Builder.this);
        }
    }

    @ChannelHandler.Sharable
    public static abstract class HandlerSet extends ChannelInboundHandlerAdapter {

        public abstract ChannelHandler[] handlers();
    }

    /**
     * 断线重连
     */
    public class ChannelDisconnectHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            ctx.fireChannelInactive();
            if (null != listener) {
                listener.onDisconnect(SessionUtil.getSession(ctx.channel()));
            }
            SessionUtil.unbindSession(ctx.channel());
            // 重连
            connect();
        }
    }
}