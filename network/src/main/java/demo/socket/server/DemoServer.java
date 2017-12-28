package demo.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServer {

    public void initServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(1219);
            SocketConnectPool connectPool = new SocketConnectPool();
            System.out.println("server start....");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("get a connect.......");

                DataInputStream input = new DataInputStream(socket.getInputStream());
                String userName = input.readUTF();

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(userName +" welcome to my server !");

                SocketManager socketManager = new SocketManager(socket);
                connectPool.putSocket(userName,socketManager);

                //bufferedReader.close();
                //pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
