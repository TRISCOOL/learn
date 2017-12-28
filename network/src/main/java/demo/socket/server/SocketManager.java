package demo.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketManager implements Runnable{

    private Socket socket;

    public SocketManager(Socket socket){
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void run() {
        /**
         * 用于管理此socket
         */
    }
}
