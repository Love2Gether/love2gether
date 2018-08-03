package online.profsoft.love2gether.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.List;

public class Dialog implements IDialog {

    private String id;
    private String dialogPhoto;
    private String dialogName;
    private ArrayList<User> users;
    private Message lastMessage;
    private ArrayList<Message> messages;
    private int unreadCount;
    private String userId1;
    private String userId2;


    public Dialog() {
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Dialog(String id, String dialogPhoto, String dialogName, ArrayList<User> users, Message lastMessage, ArrayList<Message> messages, int unreadCount) {
        this.id = id;
        this.dialogPhoto = dialogPhoto;
        this.dialogName = dialogName;
        this.users = users;
        this.lastMessage = lastMessage;
        this.messages = messages;
        this.unreadCount = unreadCount;
    }

    public Dialog(String id, String dialogPhoto, String dialogName, ArrayList<User> users, Message lastMessage, ArrayList<Message> messages, int unreadCount, String userId1, String userId2) {
        this.id = id;
        this.dialogPhoto = dialogPhoto;
        this.dialogName = dialogName;
        this.users = users;
        this.lastMessage = lastMessage;
        this.messages = messages;
        this.unreadCount = unreadCount;
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDialogPhoto(String dialogPhoto) {
        this.dialogPhoto = dialogPhoto;
    }

    public void setDialogName(String dialogName) {
        this.dialogName = dialogName;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return dialogPhoto;
    }

    @Override
    public String getDialogName() {
        return dialogName;
    }

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public Message getLastMessage() {
        return lastMessage;
    }

    @Override
    public void setLastMessage(IMessage lastMessage) {
        this.lastMessage = (Message)lastMessage;
    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }

}
