package ir.sharif.math.ap99_2.tinsta_server;

import ir.sharif.math.ap99_2.tinsta_server.controller.SocketManager;

public class Main {
    public static void main(String[] args) {
        SocketManager socketManager = new SocketManager();
        socketManager.start();
    }
}
