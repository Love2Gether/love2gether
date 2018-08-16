package online.profsoft.love2gether.database;

import java.util.ArrayList;

import online.profsoft.love2gether.models.Dialog;
import online.profsoft.love2gether.models.Message;
import online.profsoft.love2gether.models.MyDialog;
import online.profsoft.love2gether.models.MyMessage;

public class ReverseDialog {
    private static ArrayList<MyMessage> changeMessageInMyMessage(ArrayList<Message> messages) {
        ArrayList<MyMessage> myMessages = new ArrayList<>();
        for (Message mess : messages)
            myMessages.add(changeMyMessage(mess));
        return myMessages;
    }

    private static ArrayList<Message> changeMyMessageInMessage(ArrayList<MyMessage> messages) {
        ArrayList<Message> myMessages = new ArrayList<>();
        for (MyMessage mess : messages)
            myMessages.add(changeMessage(mess));
        return myMessages;
    }
    private static MyMessage changeMyMessage(Message messages) {
        MyMessage myMessages = new MyMessage(messages.getId(), messages.getText(), messages.getUser(), messages.getCreatedAt());
        return myMessages;
    }
    private static Message changeMessage(MyMessage messages) {
        Message myMessages = new Message(messages.getId(), messages.getText(), messages.getAuthor(), messages.getCreatedAt());
        return myMessages;
    }
    public static MyDialog changeDialogInMyDialog(Dialog dialog) {
        MyDialog dial = new MyDialog(dialog.getId(), dialog.getDialogPhoto(),
                dialog.getDialogName(),  changeMyMessage((Message) dialog.getLastMessage()),
                changeMessageInMyMessage(dialog.getMessages()), dialog.getUnreadCount(), dialog.getUsers().get(0).getId(), dialog.getUsers().get(1).getId(), dialog.getUsers());
        return dial;
    }
    public static Dialog changeMyDialogInDialog(MyDialog dialog) {
        if (dialog == null ) return new Dialog();
          Dialog dial = new Dialog(dialog.getId(), dialog.getDialogPhoto(),
                dialog.getDialogName(), dialog.getUsers(),
                  changeMessage(dialog.getLastMessage()), changeMyMessageInMessage(dialog.getMessages()), dialog.getUnreadCount());
        return dial;
    }
}
