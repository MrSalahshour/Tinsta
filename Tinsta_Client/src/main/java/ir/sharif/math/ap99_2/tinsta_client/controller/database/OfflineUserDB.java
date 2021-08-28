package ir.sharif.math.ap99_2.tinsta_client.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_client.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_client.model.OfflineUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class OfflineUserDB implements DBSet<OfflineUser> {
    private final File offlineUserDirectory;
    private final ObjectMapper objectMapper;

    public OfflineUserDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.offlineUserDirectory = directoryConfig.getOfflineUserDirectory();
        if (!offlineUserDirectory.exists()) offlineUserDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public OfflineUser get(int id) {
        File[] filesList = offlineUserDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            if (file.getName().equals("OfflineUser"+id+".txt")){
                return loadOfflineUser(file);
            }
        }
        return null;
    }

    @Override
    public void add(OfflineUser offlineUser) {
        try {
            File file = getOfflineUserFile(offlineUser.getUserId());
            if (file == null) {
                file = new File(offlineUserDirectory.getPath() + "\\" + "OfflineUser"+offlineUser.getUserId() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, offlineUser);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find offlineUser file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(OfflineUser offlineUser) {
        File[] filesList = offlineUserDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("OfflineUser"+offlineUser.getUserId()+".txt")){
                file.delete();
                break;
            }
        }
    }

    @Override
    public LinkedList<OfflineUser> all() {
        LinkedList<OfflineUser> allOfflineUsers = new LinkedList<>();
        File[] filesList = offlineUserDirectory.listFiles();
        if (filesList == null)
            return allOfflineUsers;
        for (File file : filesList) {
            OfflineUser offlineUser = loadOfflineUser(file);
            allOfflineUsers.add(offlineUser);
        }
        return allOfflineUsers;
    }

    @Override
    public void update(OfflineUser offlineUser) {
        add(offlineUser);
    }
    private File getOfflineUserFile(int id) {
        File file = new File(offlineUserDirectory.getPath() + "\\" + "OfflineUser"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private OfflineUser loadOfflineUser(File file){
        try {
            return objectMapper.readValue(file, OfflineUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
