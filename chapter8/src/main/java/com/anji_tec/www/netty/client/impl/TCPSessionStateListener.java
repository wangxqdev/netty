package com.anji_tec.www.netty.client.impl;

import com.anji_tec.www.netty.client.Session;
import com.anji_tec.www.netty.client.SessionStateListener;
import com.anji_tec.www.netty.util.SessionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TCPSessionStateListener implements SessionStateListener {

    private Log log = LogFactory.getLog(getClass());

    @Override
    public void onConnectSuccess(Session session) {
        log.info("Channel establish succeed, host = " + session.getHost() + ", port = " + session.getPort());
    }

    @Override
    public void onConnectFailed(Session session) {
        log.info("Channel establish failure, host = " + session.getHost() + ", port = " + session.getPort());
    }

    @Override
    public void onDisconnect(Session session) {
        // 释放资源
        SessionUtil.getChannel(session.getHost()).close();
        log.info("Channel closed, host = " + session.getHost() + ", port = " + session.getPort());
    }
}