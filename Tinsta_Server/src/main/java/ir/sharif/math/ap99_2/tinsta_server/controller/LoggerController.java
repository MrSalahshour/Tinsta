package ir.sharif.math.ap99_2.tinsta_server.controller;



import ir.sharif.math.ap99_2.tinsta_server.config.DirectoryConfig;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerController extends Controller {
    private final File logDirectory;
    private final ClientHandler clientHandler;



    public LoggerController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        DirectoryConfig directoryConfig = new DirectoryConfig();
        this.logDirectory = directoryConfig.getLogDirectory();
        if (!logDirectory.exists()) logDirectory.mkdirs();

    }

    public void log(String level, String Class, String function, String username, String content) {
        try {
            File file = getLoggerFile(level);
            if (file == null) {
                file = new File(logDirectory.getPath() + "\\"
                        + level + ".txt");
                file.createNewFile();

            }
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("Level:  " + level + "\n" +
                    "UserName: " + username + "\n" +
                    "Class: " + Class + ", " + "Function: " + function + "\n" +
                    "Content: " + content + "\n" + "Time: " + LocalDateTime.now().toString() + "\n" + "\n");

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
            assert clientHandler.getCurrentUser() != null;
            UserName userName = getContext().getUserNames().get(clientHandler.getCurrentUser().getUserNameId());
            log(LoggerController.debugLevel(), "MyLogger", "log",
                    userName.getUserNameText(), "Error in Writing Log");
        }

    }


    public static String infoLevel() {
        return "INFO";
    }

    public static String debugLevel() {
        return "DEBUG";
    }

    public static String errorLevel() {
        return "ERROR";
    }

    public File getLoggerFile(String level) {
        File file = new File(logDirectory.getPath() + "\\" + level + ".txt");
        if (!file.exists()) {
            return null;
        } else {
            return file;
        }
    }
}
