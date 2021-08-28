package ir.sharif.math.ap99_2.tinsta_client.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_client.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_shared.event.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class EventDB implements DBSet<Event> {
    private final File eventDirectory;
    private final ObjectMapper objectMapper;

    public EventDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.eventDirectory = directoryConfig.getEventDirectory();
        if (!eventDirectory.exists()) eventDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public Event get(int id) {
        File[] filesList = eventDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            if (file.getName().equals("Event"+id+".txt")){
                return loadEvent(file);
            }
        }
        return null;
    }

    @Override
    public void add(Event event) {
        try {
            File file = getEventFile(event.getID());
            if (file == null) {
                file = new File(eventDirectory.getPath() + "\\" + "Event"+event.getID() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, event);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find event file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Event event) {
        File[] filesList = eventDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("Event"+event.getID()+".txt")){
                file.delete();
                break;
            }
        }
    }

    @Override
    public LinkedList<Event> all() {
        LinkedList<Event> allEvents = new LinkedList<>();
        File[] filesList = eventDirectory.listFiles();
        if (filesList == null)
            return allEvents;
        for (File file : filesList) {
            Event event = loadEvent(file);
            allEvents.add(event);
        }
        return allEvents;
    }

    @Override
    public void update(Event event) {
        add(event);
    }

    private File getEventFile(int id) {
        File file = new File(eventDirectory.getPath() + "\\" + "Event"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private Event loadEvent(File file){
        try {
            return objectMapper.readValue(file, Event.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
