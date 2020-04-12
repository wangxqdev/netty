package com.anji_tec.www.netty.filter;

import com.anji_tec.www.netty.protocol.Packet;
import com.anji_tec.www.netty.util.SequenceUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ChannelHandler.Sharable
public class SeqNoFilter extends SimpleChannelInboundHandler<Packet> {

    private Log log = LogFactory.getLog(getClass());

    public static final SeqNoFilter INSTANCE = new SeqNoFilter();

    private SeqNoFilter() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        if (!SequenceUtil.seqNoCheck(ctx.channel(), msg.getSeqNo())) {
            log.error("Identical sequence numbers are found in the already-received text.");
            return;
        }
    }
}
