package demo.netty;

import demo.netty.server.Server;

import java.util.Random;

public class Main {
    public static void main(String[] args){
        Server server = new Server(1219);
        server.run();
    }
}
