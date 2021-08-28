package ir.sharif.math.ap99_2.tinsta_client.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_client.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class CommentDB implements DBSet<Comment> {
    private final File commentsDirectory;
    private final ObjectMapper objectMapper;

    public CommentDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.commentsDirectory = directoryConfig.getCommentsDirectory();
        if (!commentsDirectory.exists()) commentsDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public Comment get(int id) {
        File[] filesList = commentsDirectory.listFiles();
        if (filesList == null)
            return null;
        for (File file : filesList) {
            if (file.getName().equals("Comment"+id+".txt")){
                return loadComment(file);
            }
        }
        return null;
    }

    @Override
    public void add(Comment comment) {
        try {
            File file = getCommentFile(comment.getId());
            if (file == null) {
                file = new File(commentsDirectory.getPath() + "\\" + "Comment"+comment.getId() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, comment);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find comment file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Comment comment) {
        File[] filesList = commentsDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("Comment"+comment.getId()+".txt")){
                file.delete();
                break;
            }
        }
    }

    @Override
    public LinkedList<Comment> all() {
        LinkedList<Comment> allComments = new LinkedList<>();
        File[] filesList = commentsDirectory.listFiles();
        if (filesList == null)
            return allComments;
        for (File file : filesList) {
            Comment comment = loadComment(file);
            allComments.add(comment);
        }
        return allComments;
    }

    @Override
    public void update(Comment comment) {
        add(comment);
    }
    private File getCommentFile(int id) {
        File file = new File(commentsDirectory.getPath() + "\\" + "Comment"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private Comment loadComment(File file){
        try {
            return objectMapper.readValue(file, Comment.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
