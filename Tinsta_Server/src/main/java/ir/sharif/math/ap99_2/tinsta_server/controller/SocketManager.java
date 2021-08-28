package ir.sharif.math.ap99_2.tinsta_server.controller;



import ir.sharif.math.ap99_2.tinsta_server.config.MainConfig;
import ir.sharif.math.ap99_2.tinsta_server.transmitters.SocketResponseSender;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketManager extends Thread{
    private ServerSocket serverSocket;
    private volatile boolean running;


    public SocketManager() {
        try {
            Integer port;
            MainConfig config = MainConfig.getConfig("serverDetail");
            port = config.getProperty(Integer.class,"port");
            if (port == null)
                port = 8000;
            serverSocket = new ServerSocket(port);
            running = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(new SocketResponseSender(socket));
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
