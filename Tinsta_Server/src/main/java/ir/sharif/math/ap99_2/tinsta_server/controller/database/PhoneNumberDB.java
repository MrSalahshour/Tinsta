package ir.sharif.math.ap99_2.tinsta_server.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_server.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.DBSet;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.PhoneNumber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class PhoneNumberDB implements DBSet<PhoneNumber> {
    private final File phoneNumbersDirectory;
    private final ObjectMapper objectMapper;

    public PhoneNumberDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.phoneNumbersDirectory = directoryConfig.getPhoneNumbersDirectory();
        if (!phoneNumbersDirectory.exists()) phoneNumbersDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public PhoneNumber get(int id) {
        File[] filesList = phoneNumbersDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            if (file.getName().equals("PhoneNumber"+id+".txt")){
                return loadPhoneNumber(file);
            }
        }
        return null;
    }

    @Override
    public void add(PhoneNumber phoneNumber) {
        try {
            File file = getPhoneNumberFile(phoneNumber.getId());
            if (file == null) {
                file = new File(phoneNumbersDirectory.getPath() + "\\" + "PhoneNumber"+phoneNumber.getId() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, phoneNumber);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find phoneNumber file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(PhoneNumber phoneNumber) {
        File[] filesList = phoneNumbersDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("PhoneNumber"+phoneNumber.getId()+".txt")){
                file.delete();
                break;
            }
        }
    }

    @Override
    public LinkedList<PhoneNumber> all() {
        LinkedList<PhoneNumber> allPhoneNumbers = new LinkedList<>();
        File[] filesList = phoneNumbersDirectory.listFiles();
        if (filesList == null)
            return allPhoneNumbers;
        for (File file : filesList) {
            PhoneNumber phoneNumber = loadPhoneNumber(file);
            allPhoneNumbers.add(phoneNumber);
        }
        return allPhoneNumbers;
    }

    @Override
    public void update(PhoneNumber phoneNumber) {
        add(phoneNumber);

    }
    public boolean PhoneNumberExist(String phoneNumberText){
        File[] filesList = phoneNumbersDirectory.listFiles();
        if (filesList == null)
            return false;
        for (File file : filesList) {
            PhoneNumber phoneNumber = loadPhoneNumber(file);
            if (phoneNumber==null)
                continue;
            if (phoneNumber.getPhoneNumberText().equals(phoneNumberText)){
                if (!phoneNumberText.equals("NoPhoneNumber"))
                    return true;
            }

        }
        return false;
    }

    private File getPhoneNumberFile(int id) {
        File file = new File(phoneNumbersDirectory.getPath() + "\\" + "PhoneNumber"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private PhoneNumber loadPhoneNumber(File file){
        try {
            return objectMapper.readValue(file, PhoneNumber.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
