package ir.sharif.math.ap99_2.tinsta_client.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_client.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class UserNameDB implements DBSet<UserName> {
    private final File userNamesDirectory;
    private final ObjectMapper objectMapper;

    public UserNameDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.userNamesDirectory = directoryConfig.getUserNamesDirectory();
        if (!userNamesDirectory.exists()) userNamesDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public UserName get(int id) {
        File[] filesList = userNamesDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            if (file.getName().equals("UserName"+id+".txt")){
                return loadUserName(file);
            }
        }
        return null;
    }

    @Override
    public void add(UserName userName) {
        try {
            File file = getUserNameFile(userName.getId());
            if (file == null) {
                file = new File(userNamesDirectory.getPath() + "\\" + "UserName"+userName.getId() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, userName);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find userName file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(UserName userName) {
        File[] filesList = userNamesDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("UserName"+userName.getId()+".txt")){
                file.delete();
                break;
            }
        }
    }

    @Override
    public LinkedList<UserName> all() {
        LinkedList<UserName> allUserNames = new LinkedList<>();
        File[] filesList = userNamesDirectory.listFiles();
        if (filesList == null)
            return allUserNames;
        for (File file : filesList) {
            UserName userName = loadUserName(file);
            allUserNames.add(userName);
        }
        return allUserNames;
    }

    @Override
    public void update(UserName userName) {
        add(userName);

    }
    public boolean UserNameExist(String userNameText){
        File[] filesList = userNamesDirectory.listFiles();
        if (filesList == null)
            return false;
        for (File file : filesList) {
            UserName userName = loadUserName(file);
            if (userName==null)
                continue;
            if (userName.getUserNameText().equals(userNameText))
                return true;
        }
        return false;
    }
    public UserName getByUserNameText(String userNameText){
        File[] filesList = userNamesDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            UserName userName = loadUserName(file);
            if (userName==null)
                continue;
            if (userName.getUserNameText().equals(userNameText))
                return userName;
        }
        return null;
    }
    private File getUserNameFile(int id) {
        File file = new File(userNamesDirectory.getPath() + "\\" + "UserName"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private UserName loadUserName(File file){
        try {
            return objectMapper.readValue(file, UserName.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
