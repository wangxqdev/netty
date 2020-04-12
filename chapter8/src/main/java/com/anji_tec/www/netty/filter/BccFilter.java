package com.anji_tec.www.netty.filter;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.util.BccUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ChannelHandler.Sharable
public class BccFilter extends SimpleChannelInboundHandler<Packet> {

    private Log log = LogFactory.getLog(getClass());

    public static final BccFilter INSTANCE = new BccFilter();

    private BccFilter() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        if (!BccUtil.check(new String(msg.getMessageExpBcc()), msg.getBcc())) {
            log.warn("Invalid Protocol Bcc");
            return;
        }
    }
}
