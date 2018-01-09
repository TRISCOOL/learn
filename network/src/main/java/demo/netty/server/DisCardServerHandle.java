package demo.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DisCardServerHandle extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /**
         * channelRead() method is invoked whenever data is received.
         */

        ByteBuf in = (ByteBuf) msg;
        try {
            /*while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }*/

            ctx.write(msg);
            ctx.flush();

        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
