package ir.sharif.math.ap99_2.tinsta_client.user_related.listener;


import ir.sharif.math.ap99_2.tinsta_client.setting.listener.BackToMenuListener;
import ir.sharif.math.ap99_2.tinsta_client.timeline.listener.ShowTimeLineTweetListener;
import ir.sharif.math.ap99_2.tinsta_client.timeline.view.TimeLineView;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.event.GoToTimeLineEvent;

import javax.swing.*;

public class GoToTimeLineListener {
    public void eventOccurred(GoToTimeLineEvent goToTimeLineEvent)  {

        JPanel panel = (JPanel) goToTimeLineEvent.getSource();
        panel.removeAll();
        TimeLineView timeLineView = new TimeLineView(panel);
        timeLineView.setBackToLoginListener(new BackToLoginListener());
        timeLineView.setBackToMenuListener(new BackToMenuListener());
        timeLineView.setShowTimeLineTweetListener(new ShowTimeLineTweetListener());
        panel.add(timeLineView);
        panel.revalidate();
        panel.repaint();
    }
}
