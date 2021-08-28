package ir.sharif.math.ap99_2.tinsta_client.setting.listener;


import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_client.setting.view.ShowSettingStatusView;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.BackToLoginListener;
import ir.sharif.math.ap99_2.tinsta_client.user_related.listener.GoToSettingListener;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetStringResponse;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.GetLastSeenStatusEvent;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.GetPrivacyStatusEvent;
import ir.sharif.math.ap99_2.tinsta_shared.setting.event.ShowSettingStatusEvent;

import javax.swing.*;

public class ShowSettingStatusListener {

    public void eventOccurred(ShowSettingStatusEvent showSettingStatusEvent){
        JPanel panel = (JPanel) showSettingStatusEvent.getSource();
        panel.removeAll();
        ShowSettingStatusView showSettingStatusView = new ShowSettingStatusView(panel);
        GetStringResponse lastSeenStatus = (GetStringResponse) MainController
                .sendEvents(new GetLastSeenStatusEvent(this));
        GetStringResponse privacyStatus = (GetStringResponse) MainController
                .sendEvents(new GetPrivacyStatusEvent(this));
        showSettingStatusView.setLastSeenStatusLabelText(lastSeenStatus.getContent());
        showSettingStatusView.setPrivacyStatusLabelText(privacyStatus.getContent());
        showSettingStatusView.setBackToLoginListener(new BackToLoginListener());
        showSettingStatusView.setGoToSettingListener(new GoToSettingListener());
        panel.add(showSettingStatusView);
        panel.revalidate();
        panel.repaint();

    }
}
