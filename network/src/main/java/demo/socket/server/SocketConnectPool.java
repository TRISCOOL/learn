package demo.socket.server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketConnectPool {

    private Map<String,SocketManager> socketHashMap;

    public SocketConnectPool(){
        init();
        pushMessage();
    }

    private void init(){
        if (socketHashMap != null)return;
        synchronized (this){
            if (socketHashMap != null)return;
            socketHashMap = new ConcurrentHashMap<String, SocketManager>();
        }
    }

    public SocketManager getSocket(String account){
        if (account != null && socketHashMap != null && socketHashMap.size() > 0){
            return socketHashMap.get(account);
        }
        return null;
    }

    public boolean putSocket(String account,SocketManager socketManager){
        if (socketHashMap != null){
            socketHashMap.put(account,socketManager);
            return true;
        }
        return false;
    }

    private void pushMessage(){

        new Thread(new Runnable() {
            public void run() {
                while (true){
                    synchronized (this){
                        if (socketHashMap != null){
                            for (String account : socketHashMap.keySet()){
                                SocketManager socketManager = socketHashMap.get(account);
                                if (socketManager != null){
                                    Socket socket = socketManager.getSocket();
                                    try {
                                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                                        out.writeUTF("this is a message !");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
