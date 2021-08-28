package ir.sharif.math.ap99_2.tinsta_server.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_server.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_server.controller.ClientHandler;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.DBSet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


import java.io.*;
import java.util.LinkedList;

public class UserDB implements DBSet<User> {
    private final File usersDirectory;
    private final ObjectMapper objectMapper;
    private final UserNameDB userNameDB;
    private ClientHandler clientHandler;

    public UserDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.usersDirectory = directoryConfig.getUsersDirectory();
        if (!usersDirectory.exists()) usersDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        userNameDB = new UserNameDB();
    }

    @Override
    public User get(int id) {
        File[] filesList = usersDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            if (file.getName().equals("User"+id+".txt")){
                return loadUser(file);
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            File file = getUserFile(user.getId());
            if (file == null) {
                file = new File(usersDirectory.getPath() + "\\" + "User"+user.getId() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, user);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find user file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(User user) {
        File[] filesList = usersDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("User"+user.getId()+".txt")){
                file.delete();
                break;
            }
        }
    }

    @Override
    public LinkedList<User> all() {
        LinkedList<User> allUsers = new LinkedList<>();
        File[] filesList = usersDirectory.listFiles();
        if (filesList == null)
            return allUsers;
        for (File file : filesList) {
            User user = loadUser(file);
            allUsers.add(user);
        }
        return allUsers;
    }

    @Override
    public synchronized void update(User user) {
        add(user);
        if (clientHandler.getCurrentUser().getId()==user.getId()){
            clientHandler.setCurrentUser(get(user.getId()));
        }
    }

    public User getByUserName(String userName){
        File[] filesList = usersDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            User user = loadUser(file);
            if (user==null)
                continue;
            UserName username = userNameDB.get(user.getUserNameId());
            if (username.getUserNameText().equals(userName)){
                return user;
            }
        }
        return null;
    }

    private File getUserFile(int id) {
        File file = new File(usersDirectory.getPath() + "\\" + "User"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private User loadUser(File file){
        try {
            return objectMapper.readValue(file, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }
}
