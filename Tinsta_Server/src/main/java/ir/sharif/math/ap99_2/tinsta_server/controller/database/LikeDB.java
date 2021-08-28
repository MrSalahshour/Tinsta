package ir.sharif.math.ap99_2.tinsta_server.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_server.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.DBSet;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Like;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class LikeDB implements DBSet<Like> {
    private final File likesDirectory;
    private final ObjectMapper objectMapper;

    public LikeDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.likesDirectory =directoryConfig.getLikesDirectory();
        if (!likesDirectory.exists()) likesDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public Like get(int id) {
        File[] filesList = likesDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            if (file.getName().equals("Like"+id+".txt")){
                return loadLike(file);
            }
        }
        return null;
    }

    @Override
    public void add(Like like) {
        try {
            File file = getLikeFile(like.getId());
            if (file == null) {
                file = new File(likesDirectory.getPath() + "\\" + "Like"+like.getId() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, like);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find like file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Like like) {
        File[] filesList = likesDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("Like"+like.getId()+".txt")){
                file.delete();
                break;
            }
        }
    }

    @Override
    public LinkedList<Like> all() {
        LinkedList<Like> allLikes = new LinkedList<>();
        File[] filesList = likesDirectory.listFiles();
        if (filesList == null)
            return allLikes;
        for (File file : filesList) {
            Like like = loadLike(file);
            allLikes.add(like);
        }
        return allLikes;
    }

    @Override
    public void update(Like like) {
        add(like);

    }
    private File getLikeFile(int id) {
        File file = new File(likesDirectory.getPath() + "\\" + "Like"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private Like loadLike(File file){
        try {
            return objectMapper.readValue(file, Like.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
