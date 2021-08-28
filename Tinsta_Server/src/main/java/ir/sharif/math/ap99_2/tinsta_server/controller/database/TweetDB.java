package ir.sharif.math.ap99_2.tinsta_server.controller.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.sharif.math.ap99_2.tinsta_server.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.DBSet;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Tweet;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class TweetDB implements DBSet<Tweet> {
    private final File tweetsDirectory;
    private final ObjectMapper objectMapper;

    public TweetDB() {
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.tweetsDirectory = directoryConfig.getTweetsDirectory();
        if (!tweetsDirectory.exists()) tweetsDirectory.mkdirs();
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public Tweet get(int id) {
        File[] filesList = tweetsDirectory.listFiles();
        if (filesList == null){
            return null;
        }
        for (File file : filesList) {
            if (file.getName().equals("Tweet"+id+".txt")){
                return loadTweet(file);
            }
        }
        return null;
    }

    @Override
    public void add(Tweet tweet) {
        try {
            File file = getTweetFile(tweet.getId());
            if (file == null) {
                file = new File(tweetsDirectory.getPath() + "\\" + "Tweet"+tweet.getId() + ".txt");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            objectMapper.writeValue(fileWriter, tweet);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("could not find tweet file");
            System.exit(-2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Tweet tweet) {
        File[] filesList = tweetsDirectory.listFiles();
        if (filesList == null)
            return;
        for (File file : filesList) {
            if (file.getName().equals("Tweet"+tweet.getId()+".txt")){
                file.delete();
                break;
            }
        }
    }

    @Override
    public LinkedList<Tweet> all() {
        LinkedList<Tweet> allTweets = new LinkedList<>();
        File[] filesList = tweetsDirectory.listFiles();
        if (filesList == null)
            return allTweets;
        for (File file : filesList) {
            Tweet tweet = loadTweet(file);
            allTweets.add(tweet);
        }
        return allTweets;
    }

    @Override
    public void update(Tweet tweet) {
        add(tweet);
    }

    private File getTweetFile(int id) {
        File file = new File(tweetsDirectory.getPath() + "\\" + "Tweet"+id + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }

    private Tweet loadTweet(File file){
        try {
            return objectMapper.readValue(file, Tweet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
