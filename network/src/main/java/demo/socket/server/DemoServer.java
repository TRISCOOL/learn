package demo.socket.server;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class DemoServer {

    private Map<String,SocketChannel> socketChannelMap;

    public DemoServer(){
        init();
    }

    private void init(){
        if (socketChannelMap != null)return;
        synchronized (this){
            if (socketChannelMap != null)return;
            socketChannelMap = new HashMap<String, SocketChannel>();
            return;
        }
    }

    private void start(){

    }
}
