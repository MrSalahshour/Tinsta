package ir.sharif.math.ap99_2.tinsta_server.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_server.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.DBSet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.Email;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class EmailDB implements DBSet<Email> {
    private final File emailsDirectory;
    private final ObjectMapper objectMapper;

    public EmailDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.emailsDirectory = directoryConfig.getEmailsDirectory();
        if (!emailsDirectory.exists()) emailsDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public Email get(int id) {
        File[] filesList = emailsDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            if (file.getName().equals("Email"+id+".txt")){
                return loadEmail(file);
            }
        }
        return null;
    }

    @Override
    public void add(Email email) {
        try {
            File file = getEmailFile(email.getId());
            if (file == null) {
                file = new File(emailsDirectory.getPath() + "\\" + "Email"+email.getId() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, email);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find email file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Email email) {
        File[] filesList = emailsDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("Email"+email.getId()+".txt")){
                file.delete();
                break;
            }
        }

    }

    @Override
    public LinkedList<Email> all() {
        LinkedList<Email> allEmails = new LinkedList<>();
        File[] filesList = emailsDirectory.listFiles();
        if (filesList == null)
            return allEmails;
        for (File file : filesList) {
            Email email = loadEmail(file);
            allEmails.add(email);
        }
        return allEmails;
    }

    @Override
    public void update(Email email) {
        add(email);
    }

    public boolean EmailExist(String emailText){
        File[] filesList = emailsDirectory.listFiles();
        if (filesList == null)
            return false;
        for (File file : filesList) {
            Email email = loadEmail(file);
            if (email==null)
                continue;
            if (email.getEmailText().equals(emailText))
                return true;
        }
        return false;
    }
    private File getEmailFile(int id) {
        File file = new File(emailsDirectory.getPath() + "\\" + "Email"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private Email loadEmail(File file){
        try {
            return objectMapper.readValue(file, Email.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
