package com.anji_tec.www.netty.client;

/**
 * 通道状态
 */
public interface SessionStateListener {

    void onConnectSuccess(Session session);

    void onConnectFailed(Session session);

    void onDisconnect(Session session);
}
