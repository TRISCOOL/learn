package demo.nio.socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServer {

    public void runServer(){
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(1219));

            //获取通道管理器
            Selector selector = Selector.open();
            //将通道管理器与通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true){
                selector.select();
                for (SelectionKey key : selector.selectedKeys()){
                    if (key.isAcceptable()){
                        ServerSocketChannel server =
                                (ServerSocketChannel)key.channel();

                        SocketChannel channel = server.accept();
                        if (channel != null){
                            channel.configureBlocking(false);

                            channel.write(ByteBuffer.wrap(
                                    new String("send message to client").getBytes()));

                            //在与客户端连接成功后，为客户端通道注册SelectionKey.OP_READ事件。
                            channel.register(selector, SelectionKey.OP_READ);
                        }
                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(12);
                        int read = channel.read(buffer);
                        byte[] data = buffer.array();
                        String message = new String(data);

                        System.out.println("receive message from client, size:"
                                + buffer.position() + " msg: " + message);
                        channel.register(selector,SelectionKey.OP_WRITE);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        NioServer nioServer = new NioServer();
        nioServer.runServer();
    }
}
