package ir.sharif.math.ap99_2.tinsta_client.controller;

import ir.sharif.math.ap99_2.tinsta_client.config.MainConfig;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetFileResponse;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    final static ImageLoader instance = new ImageLoader();
    private final Map<String, BufferedImage> imageMap;

    public static Image getImage(String name) {
        return instance.imageMap.get(name);
    }

    public static Image getImageByPath(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ImageLoader() {
        imageMap = new HashMap<>();
        load();
    }

    private void load() {
        MainConfig mainConfig = MainConfig.getConfig("images");
        for (Map.Entry<Object, Object> k : mainConfig.entrySet()) {
            String key = (String) k.getKey();
            File file = new File((String) k.getValue());
            try {
                BufferedImage image = ImageIO.read(file);
                String name = key.replace('-', ' ');
                imageMap.put(name, image);
            } catch (IOException e) {
                System.err.println(file.toString());
                throw new RuntimeException("image file not exist", e);
            }
        }
    }

    public static String copyImageToResource(String imagePath){
        if (imagePath==null)
            return null;
        try {
            File file1 =new File(imagePath) ;
            String newName = String.valueOf(System.currentTimeMillis());
            String newPath = getDefaultImageDirectoryPath()+"\\"+newName+".png";
            File file2 = new File(newPath);
            FileUtils.copyFile(file1,file2);
            return newPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static File contentToFile(GetFileResponse getFileResponse){
        try {
            byte [] finalBytes = Base64.getDecoder().decode(getFileResponse.getContent());
            File file = new File(getDefaultImageDirectoryPath()
                    +"\\"+getFileResponse.getName());
            if (!file.exists())
                file.createNewFile();
            Path path = Paths.get(getDefaultImageDirectoryPath()
                    +"\\"+getFileResponse.getName());
            Files.write(path, finalBytes);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }





    public static String getDefaultProfileImagePath(){
        return "src/main/resources/ir/sharif/math/ap99_2/tinsta/images/unknownProfilePic.jpg";
    }
    public static String getDefaultImageDirectoryPath(){
        return "src\\main\\resources\\ir\\sharif\\math\\ap99_2\\tinsta\\images";
    }
}
