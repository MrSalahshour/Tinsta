package ir.sharif.math.ap99_2.tinsta_client;


import ir.sharif.math.ap99_2.tinsta_client.controller.Updater;
import ir.sharif.math.ap99_2.tinsta_client.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        new Updater(mainFrame.getMainPanel());

    }
}
