package ir.sharif.math.ap99_2.tinsta_server.transmitters;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ir.sharif.math.ap99_2.tinsta_shared.event.Event;

import ir.sharif.math.ap99_2.tinsta_shared.event.SetClientUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.LoginResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.ExitEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.LogOutEvent;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.RegisterEvent;



import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SocketResponseSender implements ResponseSender{
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private final Scanner scanner;
    private final PrintStream printStream;
    private String token = null;
    private final ObjectMapper objectMapper;
    private final Socket socket;
    private final Object lock;

    public SocketResponseSender(Socket socket) throws IOException {
        this.socket = socket;
        scanner = new Scanner(socket.getInputStream());
        printStream = new PrintStream(socket.getOutputStream(),true);
        this.lock = new Object();
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }


    public String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    @Override
    public Event getEvent() {
        String eventString;
        Event event;
        try {
            eventString = scanner.nextLine();
        }
        catch (NoSuchElementException e){
            return new ExitEvent();
        }
        try {
            event = objectMapper.readValue(eventString,Event.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        if (event instanceof RegisterEvent || event instanceof  SetClientUserEvent){
        }
        else {
            if (event.getToken()==null || !event.getToken().equals(token)){
                sendResponse(Response.getWrongApi());
            }
        }
        return event;
    }

    @Override
    public void sendResponse(Response response) {
        synchronized (lock){
            if (response instanceof LoginResponse && ((LoginResponse) response).isSuccess()){
                token = generateNewToken();
                ((LoginResponse) response).setToken(token);
            }
            try {
                printStream.println(objectMapper.writeValueAsString(response));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
