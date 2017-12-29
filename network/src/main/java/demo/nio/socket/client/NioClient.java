package demo.nio.socket.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class NioClient {

    public void runClient(){
        try {
            //获取socket通道
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);

            //获得通道管理器
            Selector selector=Selector.open();
            channel.connect(new InetSocketAddress("127.0.0.1", 1219));

            //为该通道注册SelectionKey.OP_CONNECT事件
            channel.register(selector, SelectionKey.OP_CONNECT);

            while (true){
                selector.select();
                for (SelectionKey key : selector.selectedKeys()){
                    if (key.isConnectable()){
                        SocketChannel read_channel=(SocketChannel)key.channel();
                        if(read_channel.isConnectionPending()){
                            read_channel.finishConnect();//如果正在连接，则完成连接
                        }
                        read_channel.register(selector, SelectionKey.OP_READ);
                    }else if (key.isReadable()){
                        SocketChannel readChannel = (SocketChannel)key.channel();
                        readChannel.configureBlocking(false);

                        ByteBuffer buffer = ByteBuffer.allocate(22);
                        readChannel.read(buffer);
                        byte[] data = buffer.array();
                        String message = new String(data);

                        System.out.println("recevie message from server:, size:"
                                + buffer.position() + " msg: " + message);

                        readChannel.register(selector,SelectionKey.OP_WRITE);
                    }else if (key.isWritable()){
                        SocketChannel writChannel = (SocketChannel) key.channel();
                        writChannel.configureBlocking(false);
                        writChannel.write(ByteBuffer.wrap(new String("push message").getBytes() ));
                        writChannel.register(selector,SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        NioClient nioClient = new NioClient();
        nioClient.runClient();
    }
}
