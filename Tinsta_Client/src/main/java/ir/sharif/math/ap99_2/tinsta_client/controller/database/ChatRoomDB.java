package ir.sharif.math.ap99_2.tinsta_client.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_client.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.model.ChatRoom;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class ChatRoomDB implements DBSet<ChatRoom> {
    private final File chatroomDirectory;
    private final ObjectMapper objectMapper;

    public ChatRoomDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.chatroomDirectory = directoryConfig.getChatroomDirectory();
        if (!chatroomDirectory.exists()) chatroomDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public ChatRoom get(int id) {
        File[] filesList = chatroomDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            if (file.getName().equals("Chatroom"+id+".txt")){
                return loadChatRoom(file);
            }
        }
        return null;
    }

    @Override
    public void add(ChatRoom chatRoom) {
        try {
            File file = getChatRoomFile(chatRoom.getID());
            if (file == null) {
                file = new File(chatroomDirectory.getPath() + "\\" + "Chatroom"+chatRoom.getID() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            
            objectMapper.writeValue(fileWriter, chatRoom);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find chatRoom file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(ChatRoom chatRoom) {
        File[] filesList = chatroomDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("Chatroom"+chatRoom.getID()+".txt")){
                file.delete();
                break;
            }
        }
    }

    @Override
    public LinkedList<ChatRoom> all() {
        LinkedList<ChatRoom> allChatRooms = new LinkedList<>();
        File[] filesList = chatroomDirectory.listFiles();
        if (filesList == null)
            return allChatRooms;
        for (File file : filesList) {
            ChatRoom chatRoom = loadChatRoom(file);
            allChatRooms.add(chatRoom);
        }
        return allChatRooms;
    }

    @Override
    public void update(ChatRoom chatRoom) {
        add(chatRoom);
    }
    private File getChatRoomFile(int id) {
        File file = new File(chatroomDirectory.getPath() + "\\" + "Chatroom"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private ChatRoom loadChatRoom(File file){
        try {
            return objectMapper.readValue(file, ChatRoom.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
