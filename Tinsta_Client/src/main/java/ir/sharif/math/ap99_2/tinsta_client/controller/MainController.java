package ir.sharif.math.ap99_2.tinsta_client.controller;



import ir.sharif.math.ap99_2.tinsta_client.config.MainConfig;
import ir.sharif.math.ap99_2.tinsta_client.listener.EventSender;
import ir.sharif.math.ap99_2.tinsta_client.listener.network.SocketEventSender;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
import ir.sharif.math.ap99_2.tinsta_shared.event.SetClientUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

import java.io.IOException;
import java.net.Socket;

public class MainController {
    private static EventSender eventSender = initialize();
    private static final Object lock = new Object();
    private static boolean connected = false;
    private static Integer userId;
    private static String token;
    private static boolean refreshed = false;


    public static Response sendEvents(Event event) {
        synchronized (lock) {
            assert eventSender != null;
            return eventSender.send(event);
        }
    }

    private static SocketEventSender initialize (){
        try {
            MainConfig config = MainConfig.getConfig("serverDetail");
            Integer port = config.getProperty(Integer.class, "port");
            if (port == null)
                port = 8000;
            String host = config.getProperty(String.class, "host");
            if (host == null)
                host = "";
            Socket socket = new Socket(host, port);

            return new SocketEventSender(socket);
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void refreshServer(){
        eventSender = initialize();
        if (connected){
            GetStringResponse getStringResponse = (GetStringResponse)
                    MainController.sendEvents(new SetClientUserEvent(userId));
            setToken(getStringResponse.getContent());
            MainController.setRefreshed(false);
        }
    }

    public static boolean isConnected() {
        return connected;
    }

    public static void setConnected(boolean connected) {
        MainController.connected = connected;
    }

    public static Integer getUserId() {
        return userId;
    }

    public static void setUserId(Integer userId) {
        MainController.userId = userId;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MainController.token = token;
    }

    public static boolean isRefreshed() {
        return refreshed;
    }

    public static void setRefreshed(boolean refreshed) {
        MainController.refreshed = refreshed;
    }
}
