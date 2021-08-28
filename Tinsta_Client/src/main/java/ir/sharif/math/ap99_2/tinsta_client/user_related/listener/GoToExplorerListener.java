package ir.sharif.math.ap99_2.tinsta_client.user_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.GoToSearchUserListener;
import ir.sharif.math.ap99_2.tinsta_client.explorer.listener.ShowExplorerTweetListener;
import ir.sharif.math.ap99_2.tinsta_client.explorer.view.ExplorerView;
import ir.sharif.math.ap99_2.tinsta_client.setting.listener.BackToMenuListener;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToExplorerEvent;

import javax.swing.*;

public class GoToExplorerListener {

    public void eventOccurred(GoToExplorerEvent goToExplorerEvent)  {

        JPanel panel = (JPanel) goToExplorerEvent.getSource();
        panel.removeAll();
        ExplorerView explorerView = new ExplorerView(panel);
        explorerView.setBackToLoginListener(new BackToLoginListener());
        explorerView.setBackToMenuListener(new BackToMenuListener());
        explorerView.setGoToSearchUserListener(new GoToSearchUserListener());
        explorerView.setShowExplorerTweetListener(new ShowExplorerTweetListener());
        panel.add(explorerView);
        panel.revalidate();
        panel.repaint();
    }

}
