package demo.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BeforeAcceptConnectHandle extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        /**
         * the channelActive() method will be invoked when a connection is established and ready to generate traffic
         */

        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis()/1000));

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                System.out.println("消息发送成功！");
            }
        }); // (4)
    }
}
