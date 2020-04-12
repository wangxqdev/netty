package com.anji_tec.www.netty.client.impl;

import com.anji_tec.www.netty.client.Session;

public class TCPSession implements Session {

    private String host;

    private int port;

    public TCPSession(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }
}
