package ir.sharif.math.ap99_2.tinsta_server.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_server.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.DBSet;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class MessageDB implements DBSet<Message> {
    private final File messagesDirectory;
    private final ObjectMapper objectMapper;

    public MessageDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.messagesDirectory = directoryConfig.getMessagesDirectory();
        if (!messagesDirectory.exists()) messagesDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public Message get(int id) {
        File[] filesList = messagesDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            if (file.getName().equals("Message"+id+".txt")){
                return loadMessage(file);
            }
        }
        return null;
    }

    @Override
    public void add(Message message) {
        try {
            File file = getMessageFile(message.getId());
            if (file == null) {
                file = new File(messagesDirectory.getPath() + "\\" + "Message"+message.getId() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, message);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find message file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Message message) {
        File[] filesList = messagesDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("Message"+message.getId()+".txt")){
                file.delete();
                break;
            }
        }

    }

    @Override
    public LinkedList<Message> all() {
        LinkedList<Message> allMessages = new LinkedList<>();
        File[] filesList = messagesDirectory.listFiles();
        if (filesList == null)
            return allMessages;
        for (File file : filesList) {
            Message message = loadMessage(file);
            allMessages.add(message);
        }
        return allMessages;
    }

    @Override
    public void update(Message message) {
        add(message);
    }
    private File getMessageFile(int id) {
        File file = new File(messagesDirectory.getPath() + "\\" + "Message"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private Message loadMessage(File file){
        try {
            return objectMapper.readValue(file, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
