package demo.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class DisCardProtocl extends ChannelInitializer<SocketChannel>{
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new BeforeAcceptConnectHandle()).addLast(new DisCardServerHandle());
    }
}
