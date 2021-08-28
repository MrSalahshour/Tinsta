package ir.sharif.math.ap99_2.tinsta_client.communication_related.listener;



import ir.sharif.math.ap99_2.tinsta_client.communication_related.view.ChatView;
import ir.sharif.math.ap99_2.tinsta_client.controller.Context;
import ir.sharif.math.ap99_2.tinsta_client.controller.MainController;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.EditMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetClientUserEvent;
import ir.sharif.math.ap99_2.tinsta_shared.event.GetMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetMessageResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetUserResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.SaveMessageResponse;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import javax.swing.*;

public class EditMessageListener {

    public void eventOccurred(EditMessageEvent editMessageEvent){
        ChatView chatView = (ChatView) editMessageEvent.getSource();
        GetUserResponse getUserResponse = (GetUserResponse) MainController.sendEvents(new GetClientUserEvent(chatView));
        User owner = getUserResponse.getUser();
        int messageId = chatView.getMessageView().getMessageId();
        GetMessageResponse getMessageResponse = (GetMessageResponse) MainController.sendEvents(new GetMessageEvent(chatView,messageId));
        Message message = getMessageResponse.getMessage();
        if (message==null){
            showError(chatView);
            return;
        }
        if (message.getOwnerId()!=owner.getId()){
            showError(chatView);
            return;
        }
        String result = JOptionPane.showInputDialog(chatView, "Edit Your Message: ",message.getContent());
        if (result!=null) {
            EditMessageEvent editMessageEventFinal = new EditMessageEvent(chatView);
            editMessageEventFinal.setMessage(message);
            editMessageEventFinal.setResult(result);
            SaveMessageResponse saveMessageResponse = (SaveMessageResponse) MainController
                    .sendEvents(editMessageEventFinal);
            Context context = new Context();
            context.getMessages().update(saveMessageResponse.getMessage());
            chatView.getMessageView().setMessageContentTextArea(result);
            chatView.revalidate();
            chatView.repaint();

        }
    }
    private void showError(ChatView chatView){
        String msg = "You Can't edit this message!";
        JOptionPane.showMessageDialog(chatView, msg,
                "Error",JOptionPane.ERROR_MESSAGE);
    }
}
