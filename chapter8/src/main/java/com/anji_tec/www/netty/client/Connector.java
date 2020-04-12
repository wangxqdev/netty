package com.anji_tec.www.netty.client;

public interface Connector {

    void setRemoteAddress(String host, int port);

    void setSessionStateListener(SessionStateListener listener);

    void connect(String host, int port);

    void connect();
}
