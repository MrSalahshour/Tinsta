package ir.sharif.math.ap99_2.tinsta_client.listener.network;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.listener.EventSender;
import ir.sharif.math.ap99_2.tinsta_client.model.OfflineUser;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;

import ir.sharif.math.ap99_2.tinsta_shared.response.LoginResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SocketEventSender implements EventSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private String token;
    private final ObjectMapper objectMapper;


    public SocketEventSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MainController.setConnected(true);
    }

    @Override
    public Response send(Event event) {
        if (token == null && MainController.getToken() != null)
            token = MainController.getToken();
        String eventString = "";
        Response response;
        event.setToken(token);
        try {
            eventString = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        printStream.println(eventString);
        String responseString = "";
        try {
            responseString = scanner.nextLine();
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, "You Have Been Disconnected!");
            MainController.setConnected(false);
            MainController.setToken(null);
            this.close();
            return null;
        }

        try {
            response = objectMapper.readValue(responseString, Response.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        if (response instanceof LoginResponse && ((LoginResponse) response).isSuccess()) {
            token = ((LoginResponse) response).getToken();
            MainController.setUserId(((LoginResponse) response).getUserId());
            MainController.setToken(token);
            MainController.setConnected(true);
            if (((LoginResponse) response).getUserName() != null)
                saveOfflineModels(((LoginResponse) response).getUser()
                        , ((LoginResponse) response).getUserName());
        }
        return response;
    }

    private void saveOfflineModels(User user, UserName userName) {
        Context context = new Context();
        OfflineUser offlineUser = new OfflineUser(user.getId(), user.getUserNameId()
                , user.getProfileImagePath(), user.getMenu().getChatRoom().getID());
        context.getOfflineUsers().add(offlineUser);
        context.getChatRooms().add(user.getMenu().getChatRoom());
        context.getUserNames().add(userName);
    }

    @Override
    public void close() {
        try {
            printStream.close();
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
