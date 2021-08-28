package ir.sharif.math.ap99_2.tinsta_server.sharing_related.controller;


import ir.sharif.math.ap99_2.tinsta_server.communication_related.controller.ChatRoomController;
import ir.sharif.math.ap99_2.tinsta_server.controller.Controller;
import ir.sharif.math.ap99_2.tinsta_server.controller.ImageLoader;
import ir.sharif.math.ap99_2.tinsta_server.controller.interfaces.GetUser;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.ForwardMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.communication_related.event.SendMessageEvent;
import ir.sharif.math.ap99_2.tinsta_shared.enums.MessageState;
import ir.sharif.math.ap99_2.tinsta_shared.response.GetMessageResponse;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.event.MessageEventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Message;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public class MessageController extends Controller implements GetUser, MessageEventVisitor {

    private final ChatRoomController chatRoomController;

    public MessageController(ChatRoomController chatRoomController) {
        this.chatRoomController = chatRoomController;
    }


    public Message makeMessage(SendMessageEvent sendMessageEvent)  {
        if (sendMessageEvent.getMessage()!=null){
            Message message = sendMessageEvent.getMessage();
            message.setState(MessageState.SEND);
            getContext().getMessages().add(message);
            return message;
        }
        User user = getCurrentUser();
        String message = sendMessageEvent.getContent();
        String imagePath = sendMessageEvent.getImagePath();
        if (imagePath!=null){
            imagePath = ImageLoader.copyImageToResource(imagePath);
        }
        Message newMessage = new Message(user.getId(), message,imagePath);
        getContext().getMessages().add(newMessage);
        return newMessage;
    }

    public Response makeMessage(ForwardMessageEvent forwardMessageEvent)  {
        User user = getCurrentUser();
        String message = forwardMessageEvent.getMessageContent();
        String imagePath = forwardMessageEvent.getImagePath();
        if (imagePath!=null){
            imagePath = ImageLoader.copyImageToResource(imagePath);
        }
        Message newMessage = new Message(user.getId(), message,imagePath);
        getContext().getMessages().add(newMessage);
        return new GetMessageResponse(newMessage);
    }


    @Override
    public User getCurrentUser() {
        return chatRoomController.getCurrentUser();
    }
}
