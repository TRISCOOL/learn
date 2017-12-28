package demo.socket.client;

import java.io.*;
import java.net.Socket;

public class DomeClient {

    public DomeClient(String account){
        try {
            Socket socket = new Socket("127.0.0.1",1219);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println(account +" 准备发送。");
            out.writeUTF(account);

            while (true){
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String response = dataInputStream.readUTF();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
