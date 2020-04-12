package com.anji_tec.www.netty.client;

public interface Client {

    Connector init();

    void connect();
}
